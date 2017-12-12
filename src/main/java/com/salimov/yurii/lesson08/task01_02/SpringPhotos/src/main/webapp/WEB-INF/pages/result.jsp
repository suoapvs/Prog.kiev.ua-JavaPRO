<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <h1>Your photo id is: ${photo_id}</h1>

    <input type="submit" value="Delete Photo" onclick="window.location='/delete/${photo_id}';"/>
    <input type="submit" value="Upload New" onclick="window.location='/';"/>

    <br/><br/><img src="/photo/${photo_id}"/>
</div>
</body>
</html>
