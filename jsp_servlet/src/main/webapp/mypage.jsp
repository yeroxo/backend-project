<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Файлы</title>
</head>
<body>
<div>${now}</div>
<h1>${path}</h1>
<hr>

<table>
    <tr>
        <th>Файл</th>
        <th>Размер</th>
        <th>Дата</th>
    </tr>

    <j:if test="${path.getParent() != null}">
        <tr>
            <td>
                <a href="/files?path=${path.getParent()}">
                    <img src="https://icons.iconarchive.com/icons/hopstarter/sleek-xp-basic/16/Folder-icon.png"
                         alt="DIR">
                    <span>..</span>
                </a>
            </td>
        </tr>
    </j:if>

    <j:forEach items="${files}" var="file">
        <j:if test="${!file.isHidden()}">
            <tr>
                <td>
                    <a href="/files?path=${file.getAbsolutePath()}">
                        <j:if test="${file.isDirectory()}">
                            <img src="https://icons.iconarchive.com/icons/hopstarter/sleek-xp-basic/16/Folder-icon.png"
                                 alt="DIR">
                        </j:if>
                        <j:if test="${file.isFile()}">
                            <img src="https://icons.iconarchive.com/icons/royalflushxx/systematrix/16/Document-icon.png"
                                 alt="FILE">
                        </j:if>
                        <span>${file.getName()}</span>
                    </a>
                </td>
            </tr>
        </j:if>
    </j:forEach>
</table>

</body>
</html>