<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26/11/2024
  Time: 4:51 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Không tìm thấy trang | FunZone</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/error.css">
</head>
<body>
<div class="error-container">
    <div class="error-content">
        <div class="error-icon">
            <i class="fas fa-ghost"></i>
        </div>
        <div class="error-text">
            <h1>404</h1>
            <div class="divider"></div>
            <h2>Không tìm thấy trang</h2>
            <p>Trang bạn đang tìm kiếm không tồn tại hoặc đã bị di chuyển.</p>
        </div>
        <div class="error-actions">
            <a href="/" class="btn-primary">
                <i class="fas fa-home"></i>
                Về trang chủ
            </a>
            <button class="btn-secondary" onclick="history.back()">
                <i class="fas fa-arrow-left"></i>
                Quay lại
            </button>
        </div>
    </div>
</div>
</body>
</html>