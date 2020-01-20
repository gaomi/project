;(function () {
    function AddLvliTable(){
        var bhHtml = '';
        $.each(faultLog.lvliData,function (i,n) {
            if(i<1){
                bhHtml+="<div>线路：<span>"+n.LINE_NAME+"</span>区间：<span>"+n.START_STA_NAME_T+" - "+n.END_STA_NAME_T+"</span>上下行：<span>"+n.UPDOWN+"</span></div>"+
                    "<div>设备名称：<span>"+n.LEVEL3_NAME+"</span>设备类型：<span>"+n.LEVEL4_NAME+"</span>设备部位：<span>"+n.DEVICE_TYPE_NAME+"</span></div>"
            }
        });
        $(".logLvliHeade").html(bhHtml);
        $('#fault_lvli_table').bootstrapTable({
            cache: false,
            // height:400,
            striped: true,
            pagination: true,
            pageSize: 5,
            pageNumber: 1,
            pageList: [10, 20, 50, 100],
            sidepagination: 'client',
            search: false,
            showRefresh: false,
            showExport: false,
            exportTypes: ['csv', 'txt', 'xml'],
            clickToselect: true,
            columns: [ {
                field: 'UUID',
                visible: false
            }, {
                    title: '病害分类',
                    formatter:function (index,value) {
                        return value.FAULT_TYPE1_NAME+" - "+value.FAULT_TYPE2_NAME
                    }
                }, {
                    field: 'FAULT_LEVEL',
                    title: '病害等级'
                },  {
                    title: '病害量',
                    formatter:function (index,value) {
                        return value.FAULT_QUANTITY+value.FAULT_UNIT_NAME
                    }
                }, {
                    field: 'INSERT_DATE',
                    title: '处理时间',
                    formatter: function (value, row, index) {
                        return timestampToTime(value);
                        console.log(value)
                    }
                },
            ],
            data:faultLog.lvliData
        });
    }

$.ajax({
    type:"post",
    url:ctx+"/module/fault/log/record",
    dataType:"json",
    data:{code:  faultLog.lvliUuid},
    success:function (data) {
    faultLog.lvliData = data.data;
        AddLvliTable()
    },
    error:function (err) {
        console.log(err)
    }
})
})();