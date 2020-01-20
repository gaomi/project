<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script>var ctx = "${ctx }";
var currentUser = "${user.userName }";
var userPageSize = "${user.pageSize }";
var userId = "${user.id }";
var userDeptId = "${user.deptId }";
</script>
<!-- REQUIRED JS SCRIPTS -->
<!-- jQuery 3 -->
<script src="${ctx }/webjars/jquery/3.3.1/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${ctx }/webjars/bootstrap/3.4.1/js/bootstrap.min.js"></script>