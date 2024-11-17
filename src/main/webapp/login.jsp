<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 17/11/2024
  Time: 10:07 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Đăng nhập - Fun Zone</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">

</head>
<body>
<div class="login-container">
  <div class="login-card">
    <div class="login-header">
      <a href="/" class="brand">
        <i class="bi bi-play-circle"></i>
        <span>FunZone</span>
      </a>
      <h1>Chào mừng trở lại!</h1>
      <p>Đăng nhập để tiếp tục trải nghiệm</p>
    </div>

    <form>
      <div class="form-floating">
        <input type="email" class="form-control" id="email" placeholder="name@example.com" required>
        <label for="email">Email</label>
      </div>

      <div class="form-floating">
        <input type="password" class="form-control" id="password" placeholder="Mật khẩu" required>
        <label for="password">Mật khẩu</label>
      </div>

      <div class="form-check">
        <div>
          <input type="checkbox" class="form-check-input" id="remember">
          <label class="form-check-label" for="remember">Ghi nhớ đăng nhập</label>
        </div>
        <a href="#" class="forgot-password">Quên mật khẩu?</a>
      </div>

      <button type="submit" class="btn btn-primary w-100 login-btn">
        Đăng nhập
      </button>

      <div class="social-login">
        <a href="#" class="social-btn">
          <i class="bi bi-google"></i>
          <span>Google</span>
        </a>
        <a href="#" class="social-btn">
          <i class="bi bi-facebook"></i>
          <span>Facebook</span>
        </a>
      </div>

      <p class="register-link">
        Chưa có tài khoản? <a href="register.html">Đăng ký ngay</a>
      </p>
    </form>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>