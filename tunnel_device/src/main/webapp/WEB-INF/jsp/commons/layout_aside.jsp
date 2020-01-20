<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${ctx }/static/images/user/user1.png" class="img-circle" alt="测试员"/>
            </div>
            <div class="pull-left info">
                <p>${user.userName }</p>
                <!-- Status -->
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form (Optional) -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <%--  <input type="text" name="q" class="form-control" placeholder="Search..."/>
                  <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>--%>
            </div>
        </form>
        <ul class="sidebar-menu" data-widget="tree">

        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>