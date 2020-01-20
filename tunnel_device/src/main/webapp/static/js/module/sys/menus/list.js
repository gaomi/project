var $sysMenuTable = $('#sys_menus_table');
var $sysMenuSearchForm = $('#sys_menus_search_form');
var menusParam={menusRow:{},treeList:{}};
;!function () {

    loadMenuTable();

}();

// 格式化按钮
function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="RoleOfadd btn-xs btn-primary" style="margin-right:15px;"><i class="fa fa-plus" ></i>&nbsp;新增</button>',
        '<button type="button" class="RoleOfedit btn-xs btn-primary" style="margin-right:15px;"><i class="fa fa-pencil-square-o" ></i>&nbsp;修改</button>',
        '<button type="button" class="RoleOfdelete btn-xs btn-primary" style="margin-right:15px;"><i class="fa fa-trash-o" ></i>&nbsp;删除</button>'
    ].join('');

}


function loadMenuTable() {
    var operateEvents = {
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.id;
            editRow = row;
            $.each(menusParam.treeList,function(i,n){
                if(n.id == row.parentId){
                    var srow = {};
                    srow.addParentId = n.id;
                    srow.name = n.name;
                    menusParam.menusRow = srow;
                }
            });
            openEditPage('sys_menus', editRow, _clickBtn);
        },
        'click .paoding_btn_pEdit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            var srow = {};
            srow.objId = -1;
            srow.addParentId = row.id;
            srow.name = row.name;
            srow.menu_level = row.parentId + "." + row.id;
            srow.url = row.url + '/';
            menusParam.menusRow = srow;
            openEditPage('sys_menus', 'add', _clickBtn);
        },
        'click .paoding_btn_del': function (e, value, row, index) {
            row['objId'] = row.id;
            editRow = row;
            deleteMenus(row);
        }

    };

    function addFunctionAlty() {
        return [
            '<button type="button" class="btn-xs btn-primary paoding_btn_pEdit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="Edit" ><i class="fa fa-pencil-square-o" ></i>&nbsp;新增</button>',
            '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="Edit"><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑</button>',
            // '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="600_600" paoding_opt="PermEdit"><i class="fa fa-pencil-square-o" ></i>&nbsp;权限分配</button>',
            '<button type="button" class="btn-danger btn-xs paoding_btn_del" style="margin-right:15px;" ><i class="fa fa-trash-o" ></i>&nbsp;删除</button>'
        ].join('');

    }

    $sysMenuTable.bootstrapTable({
        method: 'post',
        url: ctx + '/module/sys/menus/treelist',
        //data: data,
        dataType: 'json',
        idField: 'nid',
        //rootParentId: "0",
        columns: [
            /*{
                field: 'check', checkbox: true, formatter: function (value, row, index) {
                    if (row.check == true) {
                        // console.log(row.serverName);
                        //设置选中
                        return {checked: true};
                    }
                }
            },
            {field: 'name', title: '名称'},
            // {field: 'id', title: '编号', sortable: true, align: 'center'},
            // {field: 'pid', title: '所属上级'},
            {field: 'status', title: '状态', sortable: true, align: 'center', formatter: 'statusFormatter'},
            {field: 'permission', title: '权限值'},
            {field: 'operate', title: '操作', align: 'center', events: operateEvents, formatter: 'operateFormatter'},*/
            {
                field: 'selectItem',
                checkbox: true
            }, /* {
                title: '编号',
                field: 'id'
            },*/ {
                field: 'name',
                title: '名称'
            }, {
                field: 'type',
                title: '菜单类型',
                formatter: function (index, item) {
                    if (item.type === 0) return '<span class="badge badge-warning">按钮</span>';
                    if (item.type === 1) return '<span class="badge badge-success">菜单</span>';
                }
            }/*, {
                field: 'parentId',
                title: '父级'
            }*/, {
                title: '地址',
                field: 'url',
                formatter: function (index, item) {
                    return item.url === 'null' ? '' : item.url;
                }
            }, {
                field: 'seq',
                title: '排序'
            }, {
                field: 'status',
                title: '菜单状态',
                formatter: function (value, row, index) {
                    if (value === 1) return '<span class="badge bg-green">有效</span>';
                    if (value === 0) return '<span class="badge badge-success">无效</span>';
                }
            }, {
                field: 'permission',
                title: '权限标识',
                formatter: function (index, item) {
                    return item.permission;
                }
            }, {field: 'operate', title: '操作', align: 'center', events: operateEvents, formatter: addFunctionAlty}
        ],

        // bootstrap-table-treegrid.js 插件配置 -- start

        //在哪一列展开树形
        treeShowField: 'name',
        //指定父id列
        parentIdField: 'pid',
        onLoadSuccess: function (result, res, data) {

        },
        queryParams: function (params) {
            return {
                // pageSize: params.limit,
                // pageNum: params.offset / params.limit + 1,
                "params": {
                    "keyWord": $sysMenuSearchForm.find("input[name='keyWord']").val().trim()
                }
            };
        },
        responseHandler: function (result) {
            if (result.code == 200) {
                $.each(result.data, function (i, r) {
                    r.pid = parseInt(r.parentId);
                    r.nid = parseInt(r.id);
                });
                menusParam.treeList=result.data;
                return result.data;
            } else {
                return result.message;
            }
        },
        onResetView: function (data) {
            //console.log('load');
            $sysMenuTable.treegrid({
                initialState: 'collapsed',// 所有节点都折叠
                // initialState: 'expanded',// 所有节点都展开，默认展开
                treeColumn: 1,
                // expanderExpandedClass: 'glyphicon glyphicon-minus',  //图标样式
                // expanderCollapsedClass: 'glyphicon glyphicon-plus',
                onChange: function () {
                    $sysMenuTable.bootstrapTable('resetWidth');
                }
            });

            //只展开树形的第一级节点
            $sysMenuTable.treegrid('getRootNodes').treegrid('expand');

        },
        onCheck: function (row) {
            var datas = $sysMenuTable.bootstrapTable('getData');
            // 勾选子类
            selectChilds(datas, row, "id", "pid", true);

            // 勾选父类
            selectParentChecked(datas, row, "id", "pid")

            // 刷新数据
            $sysMenuTable.bootstrapTable('load', datas);
        },
        onUncheck: function (row) {
            var datas = $sysMenuTable.bootstrapTable('getData');
            selectChilds(datas, row, "id", "pid", false);
            $sysMenuTable.bootstrapTable('load', datas);
        }
        // bootstrap-table-treetreegrid.js 插件配置 -- end
    });
}

function typeFormatter(value, row, index) {
    if (value === 'menu') {
        return '菜单'
    }
    if (value === 'button') {
        return '按钮'
    }
    if (value === 'api') {
        return '接口'
    }
    return '-'
}

function statusFormatter(value, row, index) {
    if (value === 1) {
        return '<span class="label label-success">正常</span>'
    }
    return '<span class="label label-default">锁定</span>'
}

function search() {
    $sysMenuTable.bootstrapTable('refresh');
}

function refresh() {
    $sysMenuSearchForm[0].reset();
    $MB.refreshTable('sys_menus_table');
}

function deleteMenus(rows) {
    var selected = [];
    if (rows != null && rows != undefined) {
        selected.push(rows);
    } else {
        selected = $("#sys_menus_table").bootstrapTable('getSelections');
    }
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的记录！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].objId;
        if (i !== (selected_length - 1)) ids += ",";
        //if (menuName === selected[i].name) contain = true;
    }
    if (contain) {
        $MB.n_warning('勾选的记录无法删除！');
        return;
    }

    $MB.confirm({
        text: "确定删除选中记录？",
        confirmButtonText: "确定删除"
    }, function () {

            $.post(ctx + '/module/sys/menus/delete', {"ids": ids}, function (r) {
                if (r.code === 200) {
                    $MB.n_success('删除成功');
                    refresh();
                } else {
                    $MB.n_danger(r.message);
                }
            }, 'json');


    });
}

/*
*
*
*https://examples.bootstrap-table.com/index.html#extensions/treegrid.html#view-source
*
* <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-treegrid/0.2.0/css/jquery.treegrid.min.css" rel="stylesheet">
<link href="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.css" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-treegrid/0.2.0/js/jquery.treegrid.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.15.4/dist/extensions/treegrid/bootstrap-table-treegrid.min.js"></script>

<table id="table"></table>

<script>
  var $table = $('#table')

  $(function() {
    $table.bootstrapTable({
      url: 'json/treegrid.json',
      striped: true,
      sidePagination: 'server',
      idField: 'id',
      showColumns: true,
      columns: [
        {
          field: 'ck',
          checkbox: true
        },
        {
          field: 'name',
          title: '名称'
        },
        {
          field: 'status',
          title: '状态',
          sortable: true,
          align: 'center',
          formatter: 'statusFormatter'
        },
        {
          field: 'permissionValue',
          title: '权限值'
        }
      ],
      treeShowField: 'name',
      parentIdField: 'pid',
      onPostBody: function() {
        var columns = $table.bootstrapTable('getOptions').columns

        if (columns && columns[0][1].visible) {
          $table.treegrid({
            treeColumn: 1,
            onChange: function() {
              $table.bootstrapTable('resetWidth')
            }
          })
        }
      }
    })
  })

  function typeFormatter(value, row, index) {
    if (value === 'menu') {
      return '菜单'
    }
    if (value === 'button') {
      return '按钮'
    }
    if (value === 'api') {
      return '接口'
    }
    return '-'
  }

  function statusFormatter(value, row, index) {
    if (value === 1) {
      return '<span class="label label-success">正常</span>'
    }
    return '<span class="label label-default">锁定</span>'
  }
</script>
* */