<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registration Page</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dangky.css">
</head>
<body>
<div class="register-container">
  <div class="register-card">
    <div class="register-header">
      <a href="#" class="brand">
        <i class="bi bi-play-circle"></i>
        <span>FunZone</span>
      </a>
      <h1 class="h3 mb-2">Create New Account</h1>
      <p class="text-muted">Join the FunZone community today</p>
    </div>

    <form action="${pageContext.request.contextPath}/dangky" method="post" enctype="multipart/form-data">
      <div class="avatar-upload">
        <div class="avatar-preview">
          <div class="default-avatar-icon">
            <i class="bi bi-person"></i>
          </div>
          <img id="imagePreview" src="#" alt="Avatar Preview" style="display: none;">
        </div>
        <label for="imageUpload">Choose Profile Picture</label>
        <input type="file" id="imageUpload" name="avatar" accept="image/*" required>
      </div>

      <div class="form-floating">
        <input type="text" class="form-control" id="fullname" placeholder="Full Name" required name="fullname">
        <label for="fullname">Full Name</label>
      </div>

      <div class="form-floating">
        <input type="email" class="form-control" id="email" placeholder="name@example.com" required name="email">
        <label for="email">Email Address</label>
      </div>

      <div class="form-floating">
        <input type="password" class="form-control" id="password" placeholder="Password" required name="password">
        <label for="password">Password</label>
      </div>

      <div class="form-floating">
        <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password" required name="confirm_password">
        <label for="confirmPassword">Confirm Password</label>
      </div>

      <div class="form-check mb-3">
        <input type="checkbox" class="form-check-input" id="terms" required>
        <label class="form-check-label" for="terms">
          I agree to the <a href="#">Terms of Service</a> and
          <a href="#">Privacy Policy</a>
        </label>
      </div>

      <button id="registerBtn" type="submit" class="btn btn-primary w-100 register-btn">
        Register
      </button>

      <!-- Thêm thông báo lỗi, hiển thị khi có lỗi từ server -->
      <div id="errorMsg" class="alert alert-danger" style="display: none;">
        Tài khoản đã tồn tại! Vui lòng chọn email khác.
      </div>

      <div class="social-register">
        <a href="#" class="social-btn">
          <i class="bi bi-google"></i>
          <span>Google</span>
        </a>
        <a href="#" class="social-btn">
          <i class="bi bi-facebook"></i>
          <span>Facebook</span>
        </a>
      </div>

      <p class="text-center text-muted">
        Already have an account? <a href="login.html">Sign in</a>
      </p>
    </form>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
  // Image preview functionality
  function readURL(input) {
    if (input.files && input.files[0]) {
      const reader = new FileReader();

      reader.onload = function(e) {
        const preview = document.getElementById('imagePreview');
        const defaultIcon = document.querySelector('.default-avatar-icon');

        preview.style.display = 'block';
        preview.src = e.target.result;
        defaultIcon.style.display = 'none';
      }

      reader.readAsDataURL(input.files[0]);
    }
  }

  // Add event listeners for image upload
  document.getElementById('imageUpload').addEventListener('change', function() {
    readURL(this);
  });

  // Show error message if it has content (assuming error message comes from the server)
  document.addEventListener("DOMContentLoaded", function() {
    var errorMsg = document.getElementById("errorMsg"); // Đúng cú pháp
    if (errorMsg && errorMsg.innerText.trim() !== "") {
      errorMsg.style.display = "block";  // Hiển thị thông báo lỗi
    }
  });
</script>
</body>
</html>
