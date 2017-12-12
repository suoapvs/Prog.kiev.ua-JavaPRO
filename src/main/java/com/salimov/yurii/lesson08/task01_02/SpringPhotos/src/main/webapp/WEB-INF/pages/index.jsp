<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <form action="/view" method="POST">
        Photo id: <input type="text" name="photo_id">
        <input type="submit" value="Submit"/>
    </form>

    <form action="/add_photo" enctype="multipart/form-data" method="POST">
        Photo: <input type="file" name="photo">
        <input type="submit" value="Submit"/>
    </form>

    <form action="/all_photos" method="GET">
        <input type="submit" value="All uploaded photos"/>
    </form>

    <c:if test="${mapIsNotEmpty}">
        <br>
        <p>All uploaded photos</p>
        <form action="/delete" method="POST">
            <table border="2" cellpadding="5">
                <tr>
                    <th>Checkbox</th>
                    <th>Photo id</th>
                </tr>
                <c:forEach items="${photo_ids}" var="photoId">
                    <tr>
                        <td>
                            <div align="center">
                                <input type="checkbox" name="photo_ids" value="${photoId}"/>
                            </div>
                        </td>
                        <td>${photoId}</td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <br><input type="submit" value="Delete selected"/>
        </form>
    </c:if>
</div>
</body>
</html>