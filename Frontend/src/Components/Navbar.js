import React from "react";
import { Link } from "react-router-dom";
import "./cssFiles/Navbar.css";

function Navbar() {
  return (
    <nav className="navbar">
      <h1>Aris</h1>
      <ul>
<<<<<<< Updated upstream
        <li><Link to="/">Home</Link></li>
=======
        <li><Link to="/">Home Page</Link></li>
>>>>>>> Stashed changes
        <li className="highlight"><Link to="/posts">Posts</Link></li>
        <li>About Us</li>
      </ul>
    </nav>
  );
}

export default Navbar;
