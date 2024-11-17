// Handle video click on index page
document.querySelectorAll('.video-item').forEach(video => {
  video.addEventListener('click', (e) => {
    e.preventDefault();
    const videoId = video.dataset.videoId;
    window.location.href = `/detail.html?id=${videoId}`;
  });
});

// Handle video loading on detail page
if (window.location.pathname === '/detail.html') {
  const urlParams = new URLSearchParams(window.location.search);
  const videoId = urlParams.get('id') || 'dQw4w9WgXcQ'; // Default video if no ID

  const player = document.getElementById('youtube-player');
  if (player) {
    player.src = `https://www.youtube.com/embed/${videoId}?autoplay=1`;
  }

  // Handle like/dislike buttons
  const likeBtn = document.getElementById('likeBtn');
  const dislikeBtn = document.getElementById('dislikeBtn');
  
  likeBtn?.addEventListener('click', function() {
    this.classList.toggle('active');
    if (dislikeBtn.classList.contains('active')) {
      dislikeBtn.classList.remove('active');
    }
    
    // Update like count
    const likeCount = this.querySelector('span');
    const currentCount = parseInt(likeCount.textContent);
    likeCount.textContent = this.classList.contains('active') ? currentCount + 1 : currentCount - 1;
  });
  
  dislikeBtn?.addEventListener('click', function() {
    this.classList.toggle('active');
    if (likeBtn.classList.contains('active')) {
      likeBtn.classList.remove('active');
    }
    
    // Update dislike count
    const dislikeCount = this.querySelector('span');
    const currentCount = parseInt(dislikeCount.textContent);
    dislikeCount.textContent = this.classList.contains('active') ? currentCount + 1 : currentCount - 1;
  });
  
  // Handle subscribe button
  const subscribeBtn = document.getElementById('subscribeBtn');
  
  subscribeBtn?.addEventListener('click', function() {
    this.classList.toggle('subscribed');
    const subscribeText = this.querySelector('.subscribe-text');
    const unsubscribeText = this.querySelector('.unsubscribe-text');
    
    if (this.classList.contains('subscribed')) {
      subscribeText.textContent = 'Đã đăng ký';
    } else {
      subscribeText.textContent = 'Đăng ký';
    }
  });
  
  // Handle comment input
  const commentInput = document.querySelector('.comment-input');
  const commentActions = document.querySelector('.comment-actions');
  const cancelBtn = document.querySelector('.comment-btn.cancel');
  const submitBtn = document.querySelector('.comment-btn.submit');
  
  commentInput?.addEventListener('focus', function() {
    commentActions.style.height = '40px';
  });
  
  cancelBtn?.addEventListener('click', function() {
    commentInput.value = '';
    commentActions.style.height = '0';
    commentInput.blur();
  });
  
  submitBtn?.addEventListener('click', function() {
    const commentText = commentInput.value.trim();
    if (commentText) {
      // Create new comment element
      const commentsList = document.querySelector('.comments-list');
      const newComment = document.createElement('div');
      newComment.className = 'comment-item';
      newComment.innerHTML = `
        <img src="https://picsum.photos/40/40?random=${Math.random()}" alt="User" class="user-avatar">
        <div class="comment-content">
          <div class="comment-header">
            <span class="username">Bạn</span>
            <span class="comment-date">Vừa xong</span>
          </div>
          <p class="comment-text">${commentText}</p>
          <div class="comment-actions-buttons">
            <button class="comment-action-btn">
              <i class="bi bi-hand-thumbs-up"></i>
              <span>0</span>
            </button>
            <button class="comment-action-btn">
              <i class="bi bi-hand-thumbs-down"></i>
            </button>
            <button class="comment-action-btn">Phản hồi</button>
          </div>
        </div>
      `;
      
      // Add new comment to top of list
      commentsList.insertBefore(newComment, commentsList.firstChild);
      
      // Clear input and hide actions
      commentInput.value = '';
      commentActions.style.height = '0';
      commentInput.blur();
      
      // Update comment count
      const commentsCount = document.querySelector('.comments-count');
      const currentCount = parseInt(commentsCount.textContent);
      commentsCount.textContent = `${currentCount + 1} bình luận`;
    }
  });
  
  // Handle comment actions
  document.querySelector('.comments-list')?.addEventListener('click', function(e) {
    const target = e.target;
    
    // Handle like/dislike buttons
    if (target.closest('.comment-action-btn')) {
      const btn = target.closest('.comment-action-btn');
      if (btn.querySelector('.bi-hand-thumbs-up')) {
        btn.classList.toggle('active');
        const count = btn.querySelector('span');
        const currentCount = parseInt(count.textContent);
        count.textContent = btn.classList.contains('active') ? currentCount + 1 : currentCount - 1;
      }
    }
  });
}

// Toggle menu
document.getElementById('menu-toggle')?.addEventListener('click', function() {
  document.getElementById('side-menu')?.classList.toggle('collapsed');
  document.querySelector('.main-content')?.classList.toggle('expanded');
});