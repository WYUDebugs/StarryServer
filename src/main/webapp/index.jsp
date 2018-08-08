<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<html>
<body>
<h2>Hello World!</h2>
<h2>网站开发页面</h2>
<h2>尽情装逼吧</h2>
<h2>测试多文件上传</h2>
<form action="/starry/user/uploadFileList" method="post" enctype="multipart/form-data" >

    <input type="file" name="file"><br/>
    <input type="file" name="file"><br/>
    <input type="file" name="file"><br/>
    <input type="submit"value="提交"/>
</form>
<h2></h2>
<h2>测试单文件上传</h2>
<form action="/starry/user/uploadFile" method="post" enctype="multipart/form-data" >

    <input type="file" name="file"><br/>
    <input type="submit"value="提交"/>
</form>
</body>
</html>
