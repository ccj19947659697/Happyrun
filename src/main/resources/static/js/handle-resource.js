/**
 * Created by tengj on 2017/4/10.
 */
var grid_selector = "#jqGrid";
var pager_selector = "#jqGridPager";
var rowNum = 10; 			//每页显示记录数
var task = null;			//任务（新增或编辑）
var loading;
$(function(){
    $(window).resize(function(){
        $(grid_selector).setGridWidth($(window).width()*0.95);
    });

    $(grid_selector).jqGrid({
        url:"handle/queryHandleList",
        datatype: "json",
        mtype: 'POST',
        height:window.screen.height-550,
        colModel: [
            { label: 'id', name: 'id', width: 75,hidden:true},
            { label: '申诉日期', name: 'appealDate', width: 60 },
            { label: '学号', name: 'account', width: 60},
            { label: '跑步日期', name: 'runDate', width: 60 },
            { label: '申诉是否成功', name: 'success', width: 60 },
            { label: '申诉处理结果', name: 'result', width: 200 },
            { label: '申诉处理状态', name: 'status', width: 60 },

        ],

        pager: pager_selector,
        rowNum:rowNum,
        rowList:[10,30,45], //可调整每页显示的记录数
        viewrecords: true,//是否显示行数
        altRows: true,  //设置表格 zebra-striped 值
        gridview: true, //加速显示
        multiselect: true,//是否支持多选
        multiselectWidth: 40, //设置多选列宽度
        multiboxonly: true,
        shrinkToFit:true, //此属性用来说明当初始化列宽度时候的计算类型，如果为ture，则按比例初始化列宽度。如果为false，则列宽度使用colModel指定的宽度
        forceFit:true, //当为ture时，调整列宽度不会改变表格的宽度。当shrinkToFit为false时，此属性会被忽略
        autowidth: true,
        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                updatePagerIcons(table);
            }, 0);
        },
        gridComplete: function () {
            // 防止水平方向上出现滚动条
            removeHorizontalScrollBar();
        },
        jsonReader: {//jsonReader来跟服务器端返回的数据做对应
            root: "rows",   //包含实际数据的数组
            total: "total", //总页数
            records:"records", //查询出的总记录数
            repeatitems : false //指明每行的数据是可以重复的，如果设为false，则会从返回的数据中按名字来搜索元素，这个名字就是colModel中的名字
        },
        emptyrecords: '没有记录!',
        loadtext: '正在查询服务器数据...'
    });

    //设置分页按钮组
    $(grid_selector).jqGrid('navGrid',pager_selector,
        {
            edit: false,
            // edittitle:'修改',
            // edittext:'修改',
            // editicon : 'icon-pencil blue',
            // editfunc :editUser,
            add: false,
            // addtitle:'新增',
            // addtext:'新增',
            // addicon : 'icon-plus-sign purple',
            // addfunc :addUser,
            del: false,
            // deltitle:'删除',
            // deltext:'删除',
            // delicon : 'icon-trash red',
            // delfunc:delUser,
            refresh: true,
            refreshicon : 'icon-refresh green',
            beforeRefresh:refreshData,
            search: false,
            view: false,
            alertcap:"警告",
            alerttext : "请选择需要操作的用户!"
        }
    );


    //查询点击事件
    $("#queryBtn").click(function(){
        var qryAccount=$("#qryAccount").val();
        var qryRunDate=$("#qryRunDate").val();
        $(grid_selector).jqGrid('setGridParam',{
            postData:{account:qryAccount,runDate:qryRunDate},//
            //search: true,
            page:1
        }).trigger("reloadGrid");
    });
    
    //新增教程，弹出新增窗口
    // $("#addScoreBtn").click(function () {
    //     task = "add";
    //     initData();
    //     $('#myModalLabel').text('新增学生成绩');
    //     removeReadOnly();
    //     $("#addModal").modal({
    //         keyboard : false,
    //         show : true,
    //         backdrop : "static"
    //     });
    //
    // });

    //编辑对话框取消点击事件
    $('#cancelSave').click(function(){
        $("#addModal").modal('hide');
    });

    //保存教程
    $('#saveHandleBtn').click(function(){
        saveHandle();
    });

    //修改教程，弹出修改窗
    $("#modifyHandleBtn").click(function () {
        var rows=$(grid_selector).getGridParam('selarrrow');
        if(rows==0){
            // $.messager.alert("温馨提示","请选择一行记录！");
            layer.msg('请选择一行记录！', {icon: 7,time: 2000}); //2秒关闭（如果不配置，默认是3秒）
            return;
        }else if(rows.length>1){
            // $.messager.alert("温馨提示","不能同时修改多条记录！");
            layer.msg('不能同时修改多条记录！', {icon: 7,time: 2000}); //2秒关闭（如果不配置，默认是3秒）
            return;
        }else{
            var data = $(grid_selector).jqGrid('getRowData', rows[0]);
            task = "update";
            initData();
            $("#id").val(data.id);
            $("#appealDate").val(data.appealDate);
            $("#account").val(data.account);
            $("#runDate").val(data.runDate);
            $("#success").val(data.success);
            $("#result").val(data.result);
            $("#status").val(data.status);
            $('#myModalLabel').text('成绩申诉处理');
            //$('#account').readOnly="readOnly";
            //AddReadOnly();
            $("#addModal").modal({
                keyboard : false,
                show : true,
                backdrop : "static"
            });
        }
    });

    // //删除教程方法 选择多个的话，行id用逗号隔开比如 3,4
    // $("#deleteScoreBtn").click(function () {
    //     var rows=$(grid_selector).getGridParam('selarrrow');
    //     if(rows.length>0){
    //         $.messager.confirm("温馨提示", "是否确定删除所选记录？", function() {
    //             $.ajax({
    //                 url:"score/delete",
    //                 //url:"/delete",
    //                 cache: false,
    //                 type:"post",
    //                 data:{"ids": rows.join(",")},
    //                 beforeSend : function(){
    //                     loading=layer.load("正在删除中...");
    //                 },
    //                 success:function(result){
    //                     $.messager.alert(result.message);
    //                     refreshData();
    //                 },error:function(){
    //                     //$.messager.alert("温馨提示","请求错误!");
    //
    //                     alert('用户失效,请刷新后登录！');
    //                     window.location.href = "/toLogin";
    //
    //                 },
    //                 complete : function(){
    //                     layer.close(loading);
    //                 }
    //             });
    //         });
    //     }else{
    //         //两种风格的提示,layer或者messager自己选择一种用即可。
    //         // $.messager.alert("温馨提示","至少选择一行记录！");
    //         layer.msg('至少选中一行记录！', {icon: 7,time: 2000}); //2秒关闭（如果不配置，默认是3秒）
    //     }
    // })

});

function removeHorizontalScrollBar() {
    $("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width", parseInt($("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width")) + 1 + "px");
    $(grid_selector).closest(".ui-jqgrid-bdiv").css("width", parseInt($(grid_selector).closest(".ui-jqgrid-bdiv").css("width")) + 1 + "px");
}


//初始化数据
function initData(){
    $('#appealDate').val("");
    $('#account').val("");
    $('#runDate').val("");
    $('#success').val("");
    $('#result').val("");
    $('#status').val("");
}


/**
 * 保存教程（新增或修改）
 */
function saveHandle(){
    var id = $('#id').val();
    var appealDate = $('#appealDate').val();
    var account = $('#account').val();
    var runDate = $('#runDate').val();
    var success = $('#success').val();
    var result = $('#result').val();
    var status = $('#status').val();

    $.ajax({
        url: "handle/"+task,//-----
        cache: false,
        dataType:'json',
        data : {
            "id":id,
            "appealDate": appealDate,
            "account":account,
            "runDate":runDate,
            "success": success,
            "result":result,
            "status":status,
        },
        type : 'post',
        beforeSend: function () {
            // 禁用按钮防止重复提交
            $('#saveHandleBtn').attr({ disabled: "disabled"});
        },
        success: function(result){
            if(result.flag == true){

               // alert("1");


                $.messager.alert('温馨提示',result.message);
                $("#addModal").modal('hide');
                refreshData();
            }else{

                //alert("2");


                $.messager.alert('温馨提示',result.message);
            }
        },
        complete: function () {
            $('#saveHandleBtn').removeAttr("disabled");
        },
        error: function (data) {

            //alert("3");
            //window.location.href = "/toLogin?flag=1";
            //history.go(0);
           alert('用户失效,请刷新后登录！');
            window.location.href = "/toLogin";

            // console.info("error: " + data.responseText);
            // alert('温馨提示',"用户失效！");//
        }
    });
}


function refreshData(){
    $(grid_selector).jqGrid('setGridParam',{
        postData:{student_id:null,student_name:null},///-----
        page:1
    }).trigger("reloadGrid");
}



//这个是分页图标，必须添加
function updatePagerIcons(table) {
    var replacement =
        {
            'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
            'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
            'ui-icon-seek-next' : 'icon-angle-right bigger-140',
            'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
        };
    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
        console.info($class);
        if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
    });
}


// function AddReadOnly(){
//     var obj = document.getElementById("account");
//     obj.setAttribute("readOnly",true);
// }

// function removeReadOnly(){
//     var obj = document.getElementById("account");
//     //obj.setAttribute("readOnly",false);
//     obj.removeAttribute("readOnly");
// }