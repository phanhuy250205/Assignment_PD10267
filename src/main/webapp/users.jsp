<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dashboard | User Management</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style1.css">
</head>

<body class="dashboard-body">
<div class="dashboard-container">
  <!-- Sidebar -->
  <aside class="sidebar">
    <div class="sidebar-header">
      <img src="https://via.placeholder.com/40" alt="Logo" class="logo">
      <h1>Dashboard</h1>
    </div>
    <nav class="sidebar-nav">
      <a href="quanlyvideo.jsp" class="nav-link">
        <i class="fas fa-film"></i>
        <span>Videos</span>
        <span class="badge bg-primary">24</span>
      </a>
      <a href="users.jsp" class="nav-link active">
        <i class="fas fa-users"></i>
        <span>Users</span>
        <span class="badge bg-info">12</span>
      </a>


    </nav>
  </aside>

  <!-- Main Content -->
  <main class="main-content">
    <!-- Top Navigation -->
    <header class="top-nav">
      <div class="search-bar">
        <i class="fas fa-search"></i>
        <input type="text" placeholder="Search for users...">
      </div>
      <div class="top-nav-right">
        <button class="notification-btn">
          <i class="fas fa-bell"></i>
          <span class="notification-badge">3</span>
        </button>
        <div class="user-menu">
          <img src="https://via.placeholder.com/32" alt="User" class="user-avatar">
          <span class="user-name">John Doe</span>
          <i class="fas fa-chevron-down"></i>
        </div>
      </div>
    </header>

    <!-- Content Area -->
    <div class="content-area">
      <div class="content-header">
        <h2>User Management</h2>
        <button class="btn-primary" onclick="document.getElementById('addUserModal').style.display='flex'">
          <i class="fas fa-user-plus"></i> Add New User
        </button>
      </div>

      <!-- Stats Cards -->
      <div class="stats-grid">
        <div class="stat-card">
          <div class="stat-icon" style="background: rgba(74, 144, 226, 0.1);">
            <i class="fas fa-users" style="color: #4A90E2;"></i>
          </div>
          <div class="stat-info">
            <h3>Total Users</h3>
            <p>1,234</p>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-icon" style="background: rgba(16, 185, 129, 0.1);">
            <i class="fas fa-user-check" style="color: #10B981;"></i>
          </div>
          <div class="stat-info">
            <h3>Active Users</h3>
            <p>956</p>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-icon" style="background: rgba(245, 158, 11, 0.1);">
            <i class="fas fa-user-clock" style="color: #F59E0B;"></i>
          </div>
          <div class="stat-info">
            <h3>New Users</h3>
            <p>145</p>
          </div>
        </div>
      </div>

      <!-- User Table -->
      <div class="dashboard-card">
        <table class="table custom-table">
          <thead>
          <tr>
            <th>User</th>
            <th>Role</th>
            <th>Videos</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="user" items="${userlist}">
            <tr>
              <td>
                <div class="d-flex align-items-center gap-3">
                  <div class="user-avatar">
                    <img src="https://picsum.photos/40" alt="User">
                    <span class="status-indicator online"></span>
                  </div>
                  <div>
                    <div class="fw-semibold">${user.fullname}</div>
                    <div class="text-muted small">${user.email}</div>
                  </div>
                </div>
              </td>
              <td><span class="tag">${user.admin}</span></td>
              <td>24</td> <!-- This value could be dynamic based on your data -->
              <td>
                <div class="d-flex gap-2">
                  <form action="listUsers" method="post" onsubmit="return deleteUser(event)">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="userId" value="${user.id}"> <!-- Lưu trữ userId của người dùng -->
                    <button type="submit" class="btn-icon">
                      <i class="fas fa-trash"></i> Xóa
                    </button>
                  </form>


                  <script>
                    function deleteVideo(event, videoId) {
                      event.preventDefault(); // Ngừng hành động mặc định của form (không tải lại trang)

                      if (confirm('Bạn có chắc chắn muốn xóa video này?')) {
                        // Sử dụng fetch API để gửi yêu cầu xóa
                        fetch('listUsers', {
                          method: 'POST', // Sử dụng POST để xóa
                          headers: {
                            'Content-Type': 'application/x-www-form-urlencoded', // Gửi dữ liệu dưới dạng form
                          },
                          body: new URLSearchParams({
                            'action': 'delete',
                            'id': videoId,
                          })
                        })
                                .then(response => response.text()) // Nhận phản hồi từ server
                                .then(data => {
                                  // Nếu xóa thành công, loại bỏ phần tử video khỏi giao diện
                                  document.getElementById("video-" + videoId).remove(); // Xóa video khỏi danh sách
                                })
                                .catch(error => {
                                  console.error('Lỗi:', error);
                                  alert('Lỗi xảy ra trong quá trình xóa.');
                                });
                      }
                    }
                  </script>


                </div>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
