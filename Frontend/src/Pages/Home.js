import React, { useRef } from "react";
import { Link } from "react-router-dom";
import "./CSSFiles/Home.css";

function Home() {
  const videoRef = useRef(null);

  // Play video when hovered
  const playVideo = () => {
    if (videoRef.current) {
      videoRef.current.src += "?autoplay=1"; // Adds autoplay when hovered
    }
  };

  // Reset video when mouse leaves
  const stopVideo = () => {
    if (videoRef.current) {
      videoRef.current.src = "https://www.youtube.com/embed/PpLPQVyxdxk"; // Reset video
    }
  };

  return (
    <div className="container">
      {/* Navigation Bar */}
      <nav className="navbar">
        <div className="logo">Ari3</div>
        <ul className="nav-links">
          <li className="active"><Link to="/">Dashboard</Link></li>
          <li><Link to="/posts">Posts</Link></li>
          <li>About Us</li>
        </ul>
      </nav>

      {/* Main Content */}
      <div className="main-content">
        <h1>Learn, Share & Grow Together</h1>
        <button className="join-btn">Join Us Now</button>

        {/* Short Video Section */}
        <div className="video-section">
          <iframe
            ref={videoRef}
            width="600"
            height="340"
            src="https://www.youtube.com/embed/PpLPQVyxdxk"
            title="YouTube Video"
            frameBorder="0"
            allow="autoplay; encrypted-media"
            allowFullScreen
            onMouseEnter={playVideo} // Play video on hover
            onMouseLeave={stopVideo} // Reset on mouse leave
          ></iframe>
        </div>

        {/* Why Join Us Section */}
        <div className="why-join">
          <h2>Why Join Us?</h2>
          <p>Discover a vibrant community where you can explore skill-sharing posts, structured learning plans, and engaging interactions—all for free!</p>
          <ul className="info-list">
            <li>Skill-sharing posts (images, videos, and guides)</li>
            <li>Structured learning plans with progress tracking</li>
            <li>Community engagement through likes & comments</li>
            <li>Notifications for new content & interactions</li>
          </ul>
        </div>

        <div className="why-join">
          <h3>You can see the Skill Sharing Posts</h3>
          <p>
            Share your expertise and learn from others! Upload images, videos, and guides to showcase your skills, 
            whether it’s coding, photography, or design.
            Connect with a passionate community and expand your knowledge through real-world experiences.
          </p>
        </div>

        <div className="why-join">
          <h3>You can see the Learning Plans</h3>
          <p>
            Stay on track with structured learning plans! Set clear goals, add resources, and track your progress as you learn. 
            Whether you're mastering a new language or diving into AI,
            our guided plans help you stay focused and achieve your learning milestones.
          </p>
        </div>

        {/* Post Preview */}
        <div className="why-join">
          <div className="post-preview">
            <div className="post-box"></div>
            <div className="post-box"></div>
            <div className="post-box"></div>
            <div className="post-box"></div>
          </div>
        </div>
      </div>

      {/* Footer */}
      <footer className="footer">
        <p>Contact Us</p>
        <div className="social-icons">
          <span>FB</span>
          <span>Gmail</span>
          <span>LinkedIn</span>
        </div>
      </footer>
    </div>
  );
}

export default Home;
