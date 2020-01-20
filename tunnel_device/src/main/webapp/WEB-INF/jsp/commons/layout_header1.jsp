<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header class="main-header">
    <!-- Logo -->
    <a href="${ctx }/" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"></span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg">隧道设备智能化管理平台</span>
    </a>
    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">

        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu paoding_head_menu">
            <ul class="nav navbar-nav" id="main_head_menu">
                <%--<li class="header-menu">--%>
                    <%--<a class="dropdown-toggle paoding_menu_button" href="javascript:void(0);" data-url="/visual">--%>
                        <%--全网概况--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li class="header-menu">--%>
                    <%--<a class="dropdown-toggle paoding_menu_button" href="javascript:void(0);" data-url="/report">--%>
                        <%--线路概况--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li class="header-menu">--%>
                    <%--<a class="dropdown-toggle paoding_menu_button" href="javascript:void(0);" data-url="/segment">--%>
                        <%--区间详情--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li class="header-menu">--%>
                    <%--<a class="dropdown-toggle paoding_menu_button" href="javascript:void(0);" data-url="/map/index">--%>
                        <%--地图查询--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li class="header-menu">--%>
                    <%--<a class="dropdown-toggle paoding_menu_button" href="javascript:void(0);" data-url="/data">--%>
                        <%--数据管理--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li class="header-menu">--%>
                    <%--<a class="dropdown-toggle paoding_menu_button" href="javascript:void(0);" data-url="/inspect">--%>
                        <%--结构巡检--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li class="header-menu">--%>
                    <%--<a class="dropdown-toggle paoding_menu_button" href="javascript:void(0);" data-url="/sys">--%>
                        <%--系统管理--%>
                    <%--</a>--%>
                <%--</li>--%>
                <li class="dropdown user user-menu">
                    <!-- Menu Toggle Button -->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <!-- The user image in the navbar-->
                        <img src="${ctx }/static/images/user/user1.png" class="user-image" alt="测试员"/>
                        <!-- hidden-xs hides the username on small devices so only the image appears. -->
                        <span class="hidden-xs">${user.userName }</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- The user image in the menu -->
                        <li class="user-header">
                            <img src="${ctx }/static/images/user/user1.png" class="img-circle" alt="测试员"/>

                            <p>
                                ${user.userName } - 测试员
                                <small>${sysTime }</small>
                            </p>
                        </li>
                        <%-- <!-- Menu Body -->
                         <li class="user-body">
                             <div class="row">
                                 <div class="col-xs-4 text-center">
                                     <a href="#">修改密码</a>
                                 </div>
                                 <div class="col-xs-4 text-center">
                                     <a href="#">Sales</a>
                                 </div>
                                 <div class="col-xs-4 text-center">
                                     <a href="#">Friends</a>
                                 </div>
                             </div>
                             <!-- /.row -->
                         </li>--%>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="javascript:void(0);" class="btn btn-default btn-flat" paoding-modal-size="300_300" paoding_opt="PwdEdit"
                                   onclick="openEditPage('sys_user','edit',this);">修改密码</a>
                                <a href="javascript:void(0);" class="btn btn-default btn-flat" paoding-modal-size="300_300" paoding_opt="ConfigEdit"
                                   onclick="openEditPage('sys_user','edit',this);">个人配置</a>
                            </div>
                            <div class="pull-right">
                                <a href="javascript:void(0);" class="btn btn-default btn-flat" onclick="logout();">退出</a>
                            </div>
                        </li>
                    </ul>
                </li>
                <!-- Control Sidebar Toggle Button -->
                <%-- <li class="dropdown">
                     <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                 </li>--%>
            </ul>
        </div>
        <!-- Sidebar toggle button-->
