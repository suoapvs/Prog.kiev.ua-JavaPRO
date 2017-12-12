<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Archive</title>
</head>
<body>
<div align="center">
    <h1>Archive</h1>
    <form action="zip" enctype="multipart/form-data" method="POST">
        <p>File to upload:</p>
        <input type="file" name="file">
        <p><input type="submit" value="Zip"></p>
    </form>
    </div>
</body>
</html>
