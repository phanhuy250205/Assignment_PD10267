<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 3/12/2024
  Time: 6:36 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>Share Video</title>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/linkchiase.css">
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous"
  />
</head>
<body>
<div class="share-container">
  <div class="share-card">
    <h2 class="share-title">Share Video</h2>

    <!-- Video xem trước -->
    <div class="video-container">
      <iframe
              width="100%"
              height="315"
              src="${videoSrc}"
              frameborder="0"
              allowfullscreen
              class="video-frame">
      </iframe>
    </div>

    <!-- Link chia sẻ -->
    <div class="share-options">
      <div class="share-link">
        <input type="text" readonly class="link-input" value="${shareLink}">
      </div>
      <button onclick="copyToClipboard()" class="btn btn-primary">Sao chép link</button>
      <div class="share-buttons">
        <a href="#" class="share-button whatsapp">
          Share on Gmail
        </a>
        <a href="#" class="share-button facebook">
          Share on Facebook
        </a>
        <a href="${pageContext.request.contextPath}/index" class="share-button twitter">
          Quay Lại Trang chủ
        </a>
      </div>
    </div>
  </div>
</div>

<script>
  function copyToClipboard() {
    const input = document.querySelector('.link-input');
    input.select();
    input.setSelectionRange(0, 99999); // Cho mobile devices
    navigator.clipboard.writeText(input.value).then(() => {
      alert('Link đã được sao chép!');
    });
  }
</script>
</body>
</html>






