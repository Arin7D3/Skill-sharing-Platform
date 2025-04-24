import React from "react";
import "./cssFiles/Sidebar.css";

function Sidebar() {
  return (
    <aside className="sidebar">
      <div className="menu-icon">☰</div>
      <ul>
        <li>Profile</li>
        <li>Grow</li>
        <li className="highlight">Home</li>
      </ul>
    </aside>
  );
}

export default Sidebar;
