<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 18/11/2024
  Time: 11:26 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
      <a href="${pageContext.request.contextPath}/admin/dashboard.jsp" class="nav-link">
        <i class="fas fa-chart-bar"></i>
        <span>Dashboard</span>
      </a>
      <a href="#" class="nav-link">
        <i class="fas fa-cog"></i>
        <span>Settings</span>
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
            <span class="trend positive">↑  <span class="trend positive">↑ 8% from last month</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-icon" style="background: rgba(16, 185, 129, 0.1);">
            <i class="fas fa-user-check" style="color: #10B981;"></i>
          </div>
          <div class="stat-info">
            <h3>Active Users</h3>
            <p>956</p>
            <span class="trend positive">↑ 12% from last month</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-icon" style="background: rgba(245, 158, 11, 0.1);">
            <i class="fas fa-user-clock" style="color: #F59E0B;"></i>
          </div>
          <div class="stat-info">
            <h3>New Users</h3>
            <p>145</p>
            <span class="trend positive">↑ 15% from last month</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-icon" style="background: rgba(239, 68, 68, 0.1);">
            <i class="fas fa-user-slash" style="color: #EF4444;"></i>
          </div>
          <div class="stat-info">
            <h3>Inactive Users</h3>
            <p>278</p>
            <span class="trend negative">↑ 3% from last month</span>
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
            <th>Status</th>
            <th>Last Login</th>
            <th>Videos</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>
              <div class="d-flex align-items-center gap-3">
                <div class="user-avatar">
                  <img src="https://picsum.photos/40" alt="User">
                  <span class="status-indicator online"></span>
                </div>
                <div>
                  <div class="fw-semibold">John Doe</div>
                  <div class="text-muted small">john@example.com</div>
                </div>
              </div>
            </td>
            <td><span class="tag">Administrator</span></td>
            <td><span class="status-badge success">Active</span></td>
            <td>
              <div>2 minutes ago</div>
              <div class="text-muted small">Jan 25, 2024</div>
            </td>
            <td>24</td>
            <td>
              <div class="d-flex gap-2">
                <button class="btn-icon">
                  <i class="fas fa-pencil"></i>
                </button>
                <button class="btn-icon">
                  <i class="fas fa-trash"></i>
                </button>
                <button class="btn-icon">
                  <i class="fas fa-ellipsis-v"></i>
                </button>
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <div class="d-flex align-items-center gap-3">
                <div class="user-avatar">
                  <img src="https://picsum.photos/41" alt="User">
                  <span class="status-indicator offline"></span>
                </div>
                <div>
                  <div class="fw-semibold">Jane Smith</div>
                  <div class="text-muted small">jane@example.com</div>
                </div>
              </div>
            </td>
            <td><span class="tag">Editor</span></td>
            <td><span class="status-badge warning">Away</span></td>
            <td>
              <div>3 hours ago</div>
              <div class="text-muted small">Jan 25, 2024</div>
            </td>
            <td>15</td>
            <td>
              <div class="d-flex gap-2">
                <button class="btn-icon">
                  <i class="fas fa-pencil"></i>
                </button>
                <button class="btn-icon">
                  <i class="fas fa-trash"></i>
                </button>
                <button class="btn-icon">
                  <i class="fas fa-ellipsis-v"></i>
                </button>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </main>
</div>

<!-- Add User Modal -->
<div id="addUserModal" class="modal">
  <div class="modal-content">
    <div class="modal-header">
      <h3><i class="fas fa-user-plus"></i> Add New User</h3>
      <button class="close-btn" onclick="document.getElementById('addUserModal').style.display='none'">
        <i class="fas fa-times"></i>
      </button>
    </div>
    <div class="modal-body">
      <form class="upload-form">
        <div class="form-row">
          <div class="form-group">
            <label>First Name</label>
            <input type="text" placeholder="Enter first name">
          </div>
          <div class="form-group">
            <label>Last Name</label>
            <input type="text" placeholder="Enter last name">
          </div>
        </div>
        <div class="form-group">
          <label>Email Address</label>
          <input type="email" placeholder="Enter email address">
        </div>
        <div class="form-row">
          <div class="form-group">
            <label>Password</label>
            <input type="password" placeholder="Enter password">
          </div>
          <div class="form-group">
            <label>Confirm Password</label>
            <input type="password" placeholder="Confirm password">
          </div>
        </div>
        <div class="form-row">
          <div class="form-group">
            <label>Role</label>
            <select>
              <option value="">Select role</option>
              <option value="admin">Administrator</option>
              <option value="editor">Editor</option>
              <option value="viewer">Viewer</option>
            </select>
          </div>
          <div class="form-group">
            <label>Status</label>
            <select>
              <option value="active">Active</option>
              <option value="inactive">Inactive</option>
            </select>
          </div>
        </div>
        <div class="form-group">
          <label>Profile Picture</label>
          <div class="upload-area">
            <i class="fas fa-user-circle"></i>
            <p>Click to upload profile picture or drag and drop</p>
            <input type="file" hidden>
          </div>
        </div>
      </form>
    </div>
    <div class="modal-footer">
      <button class="btn-secondary" onclick="document.getElementById('addUserModal').style.display='none'">Cancel</button>
      <button class="btn-primary">Create User</button>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>