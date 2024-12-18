<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19/11/2024
  Time: 2:10 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Dashboard - Fun Zone</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
</head>
<body>
<div class="admin-layout">
    <!-- Sidebar -->
    <nav class="admin-sidebar">
        <div class="sidebar-header">
            <h3>Fun Zone Admin</h3>
        </div>
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/admin/dashboard.jsp">
                    <i class="bi bi-speedometer2"></i> Dashboard
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/index">
                    <i class="bi bi-speedometer2"></i>LOG OUT
                </a>
            </li>
        </ul>
    </nav>

    <!-- Main Content -->
    <main class="admin-main">
        <div class="admin-header">
            <h2>Dashboard</h2>
            <div class="admin-profile">
                <span>Admin User</span>
                <img src="https://via.placeholder.com/32" alt="Admin" class="rounded-circle">
            </div>
        </div>

        <!-- Stats Cards -->
        <div class="row g-4 mb-4">
            <div class="col-md-3">
                <div class="stats-card">
                    <div class="stats-icon bg-primary">
                        <i class="bi bi-play-circle"></i>
                    </div>
                    <div class="stats-info">
                        <h4>Total Videos</h4>
                        <span>124</span>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="stats-card">
                    <div class="stats-icon bg-success">
                        <i class="bi bi-people"></i>
                    </div>
                    <div class="stats-info">
                        <h4>Users</h4>
                        <span>1,234</span>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="stats-card">
                    <div class="stats-icon bg-info">
                        <i class="bi bi-heart"></i>
                    </div>
                    <div class="stats-info">
                        <h4>Total Likes</h4>
                        <span>45.2K</span>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="stats-card">
                    <div class="stats-icon bg-warning">
                        <i class="bi bi-share"></i>
                    </div>
                    <div class="stats-info">
                        <h4>Total Shares</h4>
                        <span>12.8K</span>
                    </div>
                </div>
            </div>
        </div>

        <!-- Recent Activity -->
        <div class="card">
            <div class="card-header">
                <h5 class="card-title mb-0">Recent Activity</h5>
            </div>
            <div class="card-body">
                <div class="activity-list">
                    <div class="activity-item">
                <span class="activity-icon bg-success">
                  <i class="bi bi-plus-lg"></i>
                </span>
                        <div class="activity-content">
                            <p>New video uploaded: "Comedy Sketch #12"</p>
                            <small>2 minutes ago</small>
                        </div>
                    </div>
                    <div class="activity-item">
                <span class="activity-icon bg-primary">
                  <i class="bi bi-person"></i>
                </span>
                        <div class="activity-content">
                            <p>New user registered: John Doe</p>
                            <small>15 minutes ago</small>
                        </div>
                    </div>
                    <div class="activity-item">
                <span class="activity-icon bg-warning">
                  <i class="bi bi-pencil"></i>
                </span>
                        <div class="activity-content">
                            <p>Video updated: "Best Moments 2024"</p>
                            <small>1 hour ago</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
