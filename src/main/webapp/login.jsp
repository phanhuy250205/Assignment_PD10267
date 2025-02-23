<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Đăng nhập - Fun Zone</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css?v=${now}">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css?v=${now}">
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<div class="login-container">
  <div class="login-card">
    <div class="login-header text-center">
      <a href="${pageContext.request.contextPath}/" class="brand">
        <i class="bi bi-play-circle"></i>
        <span>FunZone</span>
      </a>
      <h1>Chào mừng trở lại!</h1>
      <p>Đăng nhập để tiếp tục trải nghiệm</p>
    </div>

    <%-- Hiển thị thông báo lỗi nếu có --%>
    <c:if test="${not empty error}">
      <div class="alert alert-danger text-center" role="alert" id="error">
        <c:out value="${error}" />
      </div>
    </c:if>

    <%-- Hiển thị thông báo thành công nếu có --%>
    <c:if test="${not empty sessionScope.success}">
      <div class="alert alert-success text-center" role="alert" id="successMsg">
        <c:out value="${sessionScope.success}" />
      </div>
      <c:remove var="success" scope="session"/>
    </c:if>




    <form action="${pageContext.request.contextPath}/login" method="post">
      <div class="form-floating mb-3">
        <input
                name="username"
                type="text"
                class="form-control"
                id="username"
                placeholder="Tên đăng nhập"
                aria-label="Tên đăng nhập"
                aria-required="true"
                required
                autofocus
                value="<c:out value='${param.username}' />">
        <label for="username">Tên đăng nhập</label>
      </div>

      <div class="form-floating mb-3">
        <input
                name="password"
                type="password"
                class="form-control"
                id="password"
                placeholder="Mật khẩu"
                aria-label="Mật khẩu"
                aria-required="true"
                required>
        <label for="password">Mật khẩu</label>
      </div>

      <div class="form-check d-flex justify-content-between mb-3">
        <div>
          <input
                  type="checkbox"
                  class="form-check-input"
                  id="remember"
                  name="remember">
          <label class="form-check-label" for="remember">
            Ghi nhớ đăng nhập
          </label>
        </div>
        <a href="${pageContext.request.contextPath}/doimatkhau.jsp" class="forgot-password">Quên mật khẩu?</a>
      </div>

      <button id="loginBtn" type="submit" class="btn btn-primary w-100 login-btn mb-3">
        Đăng nhập
      </button>

      <div class="social-login mb-3">
        <a href="${pageContext.request.contextPath}/oauth2/google" class="btn btn-outline-secondary w-100 mb-2">
          <i class="bi bi-google me-2"></i>
          <span>Đăng nhập với Google</span>
        </a>
        <a href="${pageContext.request.contextPath}/oauth2/facebook" class="btn btn-outline-secondary w-100">
          <i class="bi bi-facebook me-2"></i>
          <span>Đăng nhập với Facebook</span>
        </a>
      </div>

      <p class="text-center register-link">
        Chưa có tài khoản?
        <a href="${pageContext.request.contextPath}/register.jsp">Đăng ký ngay</a>
      </p>
    </form>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
