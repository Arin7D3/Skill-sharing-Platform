import React from "react";
import { Link } from "react-router-dom";
import "./CSSFiles/Posts.css";

function Posts() {
  return (
    <div className="container">
      {/* Top Navbar */}
      <nav className="navbar">
        <h1>Aris</h1>
        <ul>
          <li><Link to="/">Dashboard</Link></li>
          <li className="highlight"><Link to="/posts">Posts</Link></li>
          <li>About Us</li>
        </ul>
      </nav>

      <div className="content">
        {/* Sidebar */}
        <aside className="sidebar">
          <div className="menu-icon">☰</div>
          <ul>
            <li>Profile</li>
            <li>Grow</li>
            <li className="highlight"><Link to="/Posts">Home</Link></li>
          </ul>
        </aside>

        {/* Main Content */}
        <main className="main-content">
          <h2>Posts</h2>
          <div className="posts">
            <div className="post-card">Skill-Sharing Posts</div>
            <div className="post-card">Learning Plans</div>
          </div>

          
        </main>

        {/* New buttons on the right side */}
      <div className="right-buttons">
        <button className="create-post">
          Create Post
        </button>
        <button className="add-learning-plan">
          Add New Learning Plan
        </button>
      </div>
      </div>
    </div>
  );
}

export default Posts;
