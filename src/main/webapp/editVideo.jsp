<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22/11/2024
  Time: 11:32 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh Sửa Video - Fun Zone</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/edit.css">
</head>
<body>
<form action="listvideo?action=update" method="post">
    <div class="edit-video-container">
        <nav class="top-nav">
            <div class="nav-content">
                <a href="/" class="brand">
                    <i class="bi bi-play-circle"></i>
                    <span>FunZone</span>
                </a>
                <button class="btn btn-primary save-btn">
                    <i class="bi bi-check-lg"></i>
                    Lưu thay đổi
                </button>
                <a href="listvideo?action=list">Quay lại danh sách</a>
            </div>
        </nav>

        <div class="edit-content">
            <div class="edit-form">
                <div class="edit-section">
                    <h3>Thông tin video</h3>
                    <div class="form-group">
                        <label>ID video</label>
                        <input type="text" class="form-control" placeholder="Nhập ID Video"name="id" value="${video.id}">
                    </div>
                    <div class="form-group">
                        <label>Link YouTube</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="youtubeLink"
                                   placeholder="Nhập link YouTube (ví dụ: https://www.youtube.com/watch?v=...)" name="poster" value="${video.poster}">
                            <button class="btn btn-outline-light" onclick="loadYouTubeVideo()">
                                <i class="bi bi-arrow-right-circle"></i>
                            </button>
                        </div>
                    </div>

                    <div class="video-preview" id="videoPreview">
                        <div class="ratio ratio-16x9">
                            <iframe id="youtubePlayer" src="" allowfullscreen></iframe>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>Tiêu đề</label>
                        <input type="text" class="form-control" placeholder="Nhập tiêu đề video" name="title" value="${video.title}">
                    </div>

                    <div class="form-group">
                    <label for="views">Lượt xem:</label>
                    <input type="number" name="views" id="views" value="${video.views}">
                    </div>
                    <div class="form-group">
                        <label>Mô tả</label>
                        <textarea class="form-control" rows="3" placeholder="Nhập mô tả video" name="description">${video.description}</textarea>
                    </div>

                    <div class="form-group">
                        <label for="active">Trạng thái:</label>
                        <select name="active" id="active">
                            <option value="true" ${video.active ? 'selected' : ''}>Hoạt động</option>
                            <option value="false" ${!video.active ? 'selected' : ''}>Không hoạt động</option>
                        </select>
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="allowComments" checked>
                        <label class="form-check-label" for="allowComments">Cho phép bình luận</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script>
    function loadYouTubeVideo() {
        const linkInput = document.getElementById('youtubeLink');
        const player = document.getElementById('youtubePlayer');
        const preview = document.getElementById('videoPreview');

        const videoId = extractVideoId(linkInput.value);

        if (videoId) {
            player.src = `https://www.youtube.com/embed/${videoId}`;
            preview.style.display = 'block';
        } else {
            alert('Link YouTube không hợp lệ');
        }
    }

    function extractVideoId(url) {
        const regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
        const match = url.match(regExp);
        return (match && match[2].length === 11) ? match[2] : null;
    }
</script>
</body>
</html>
