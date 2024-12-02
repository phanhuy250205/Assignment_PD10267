<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đổi Mật Khẩu - Fun Zone</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/doimatkhau.css">
</head>
<body>
<div class="change-password-container">
    <div class="change-password-card">
        <div class="card-header">
            <a href="/" class="brand">
                <i class="bi bi-play-circle"></i>
                <span>FunZone</span>
            </a>
            <h1>Đổi Mật Khẩu</h1>
            <p>Cập nhật mật khẩu mới để bảo vệ tài khoản của bạn</p>
        </div>

        <form id="changePasswordForm" action="changePassword" method="post">
            <!-- Email -->
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="email" placeholder="Email" required name="email">
                <label for="email">Email</label>
            </div>

            <!-- Old Password -->
            <div class="form-floating mb-3">
                <input type="password" class="form-control" id="currentPassword" placeholder="Mật khẩu hiện tại" required name="oldPassword">
                <label for="currentPassword">Mật khẩu hiện tại</label>
                <button type="button" class="password-toggle" onclick="togglePassword('currentPassword')">
                    <i class="bi bi-eye"></i>
                </button>
            </div>

            <!-- New Password -->
            <div class="form-floating mb-3">
                <input type="password" class="form-control" id="newPassword" placeholder="Mật khẩu mới" required name="newPassword">
                <label for="newPassword">Mật khẩu mới</label>
                <button type="button" class="password-toggle" onclick="togglePassword('newPassword')">
                    <i class="bi bi-eye"></i>
                </button>
            </div>

            <div class="password-strength mb-3">
                <div class="strength-meter">
                    <div class="meter-bar" id="strengthMeter"></div>
                </div>
                <div class="strength-text" id="strengthText">Độ mạnh mật khẩu</div>
            </div>

            <!-- Password Requirements -->
            <div class="password-requirements mb-4">
                <h6>Yêu cầu mật khẩu:</h6>
                <div class="requirement-item" id="lengthReq">
                    <i class="bi bi-x-circle"></i>
                    <span>Ít nhất 8 ký tự</span>
                </div>
                <div class="requirement-item" id="upperReq">
                    <i class="bi bi-x-circle"></i>
                    <span>Ít nhất 1 chữ hoa</span>
                </div>
                <div class="requirement-item" id="lowerReq">
                    <i class="bi bi-x-circle"></i>
                    <span>Ít nhất 1 chữ thường</span>
                </div>
                <div class="requirement-item" id="numberReq">
                    <i class="bi bi-x-circle"></i>
                    <span>Ít nhất 1 số</span>
                </div>
                <div class="requirement-item" id="specialReq">
                    <i class="bi bi-x-circle"></i>
                    <span>Ít nhất 1 ký tự đặc biệt</span>
                </div>
            </div>

            <!-- Confirm Password -->
            <div class="form-floating mb-4">
                <input type="password" class="form-control" id="confirmPassword" placeholder="Xác nhận mật khẩu mới" name="confirmPassword" required>
                <label for="confirmPassword">Xác nhận mật khẩu mới</label>
                <button type="button" class="password-toggle" onclick="togglePassword('confirmPassword')">
                    <i class="bi bi-eye"></i>
                </button>
            </div>

            <!-- Error Message -->
            <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("error") %>
            </div>
            <% } %>

            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary w-100 change-password-btn">
                <i class="bi bi-shield-lock"></i>
                Cập nhật mật khẩu
            </button>

            <!-- Additional Actions -->
            <div class="additional-actions">
                <a href="/" class="btn btn-outline-light w-100">
                    <i class="bi bi-arrow-left"></i>
                    Quay lại trang chủ
                </a>
            </div>
        </form>
    </div>
</div>

<script>
    // Password strength checker function
    function checkPasswordStrength(password) {
        let strength = 0;
        const requirements = {
            length: password.length >= 8,
            upper: /[A-Z]/.test(password),
            lower: /[a-z]/.test(password),
            number: /[0-9]/.test(password),
            special: /[^A-Za-z0-9]/.test(password)
        };

        // Update icons for each requirement
        Object.keys(requirements).forEach(req => {
            const element = document.getElementById(req + 'Req');
            if (element) {
                const icon = element.querySelector('i');
                if (requirements[req]) {
                    icon.className = 'bi bi-check-circle';
                    icon.style.color = '#10B981';
                    strength++;
                } else {
                    icon.className = 'bi bi-x-circle';
                    icon.style.color = '#EF4444';
                }
            }
        });

        // Update strength bar
        const strengthMeter = document.getElementById('strengthMeter');
        const strengthText = document.getElementById('strengthText');
        const percentage = (strength / 5) * 100;
        strengthMeter.style.width = percentage + '%';

        if (percentage <= 20) {
            strengthMeter.style.backgroundColor = '#EF4444';
            strengthText.textContent = 'Rất yếu';
        } else if (percentage <= 40) {
            strengthMeter.style.backgroundColor = '#F59E0B';
            strengthText.textContent = 'Yếu';
        } else if (percentage <= 60) {
            strengthMeter.style.backgroundColor = '#FCD34D';
            strengthText.textContent = 'Trung bình';
        } else if (percentage <= 80) {
            strengthMeter.style.backgroundColor = '#10B981';
            strengthText.textContent = 'Mạnh';
        } else {
            strengthMeter.style.backgroundColor = '#059669';
            strengthText.textContent = 'Rất mạnh';
        }
    }

    // Toggle password visibility
    function togglePassword(inputId) {
        const input = document.getElementById(inputId);
        const icon = input.nextElementSibling.nextElementSibling.querySelector('i');

        if (input.type === 'password') {
            input.type = 'text';
            icon.className = 'bi bi-eye-slash';
        } else {
            input.type = 'password';
            icon.className = 'bi bi-eye';
        }
    }

    // Password strength event listener
    document.getElementById('newPassword').addEventListener('input', function(e) {
        checkPasswordStrength(e.target.value);
    });
</script>

</body>
</html>
