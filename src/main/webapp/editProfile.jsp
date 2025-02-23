
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh Sửa Thông Tin - Fun Zone</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/editProfile.css">
</head>
<body>
<div class="edit-profile-container">
    <div class="edit-profile-card">
        <div class="profile-header">
            <a href="/" class="brand">
                <i class="bi bi-play-circle"></i>
                <span>FunZone</span>
            </a>
            <h1>Chỉnh Sửa Thông Tin</h1>
            <p>Cập nhật thông tin cá nhân của bạn</p>
        </div>

        <form class="profile-form" action="${pageContext.request.contextPath}/thongtin" method="post" enctype="multipart/form-data">
            <!-- Avatar Section -->
            <div class="avatar-section">
                <div class="avatar-wrapper">
                    <!-- Hiển thị ảnh nếu có, nếu không sẽ hiển thị ảnh mặc định -->
                    <img src="${pageContext.request.contextPath}/assets/img/${profileImage != null && !profileImage.isEmpty() ? profileImage : 'default-avatar.png'}" alt="Avatar" id="avatarPreview" />
                    <div class="avatar-overlay">
                        <i class="bi bi-camera"></i>
                    </div>
                    <input type="file" id="avatarInput" name="avatar" accept="image/*" hidden onchange="previewImage(event)">
                </div>
                <button type="button" class="btn btn-outline-light change-avatar-btn" onclick="document.getElementById('avatarInput').click()">
                    <i class="bi bi-camera"></i>
                    Đổi ảnh đại diện
                </button>
            </div>

            <!-- Form Section -->
            <div class="form-section">
                <!-- Full Name -->
                <div class="form-floating">
                    <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Họ và Tên" value="${user.fullname}">
                    <label for="fullname">Họ và Tên</label>
                </div>

                <!-- Email -->
                <div class="form-floating">
                    <input type="email" name="email" class="form-control" id="email" placeholder="Email" value="${user.email}">
                    <label for="email">Email</label>
                </div>

                <!-- Giới thiệu bản thân -->



                <!-- Notification Settings -->
                <div class="notification-settings">
                    <h6>Cài đặt thông báo</h6>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="emailNotif" >
                        <label class="form-check-label" for="emailNotif">Nhận thông báo qua email</label>
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="pushNotif" >
                        <label class="form-check-label" for="pushNotif">Nhận thông báo đẩy</label>
                    </div>
                </div>
            </div>

            <!-- Form Actions -->
            <div class="form-actions">
                <button type="button" class="btn btn-outline-light" onclick="window.location.href='/'">Hủy</button>
                <button type="submit" class="btn btn-primary save-btn">
                    <i class="bi bi-check-lg"></i>
                    Lưu thay đổi
                </button>
            </div>
        </form>

    </div>
</div>

<script>
    // Xử lý preview ảnh đại diện
    document.getElementById('avatarInput').addEventListener('change', function(e) {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('avatarPreview').src = e.target.result;
            }
            reader.readAsDataURL(file);
        }
    });

    // Xử lý form submit
    document.querySelector('form').addEventListener('submit', function(e) {
        // Thêm logic xử lý cập nhật thông tin ở đây
        alert('Thông tin đã được cập nhật thành công!');
    });
</script>
</body>
</html>