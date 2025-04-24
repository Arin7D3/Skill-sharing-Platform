import React from "react";
import { Link } from "react-router-dom";
import "./cssFiles/Navbar.css";

function Navbar() {
  return (
    <nav className="navbar">
      <h1>Aris</h1>
      <ul>
        <li><Link to="/">Home</Link></li>
        <li className="hghlight"><Link to="/posts">Posts</Link></li>
        <li>About Us</li>
      </ul>
    </nav>
  );
}

export default Navbar;
