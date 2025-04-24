import React from "react";
import "./cssFiles/RightPanel.css";

function RightPanel() {
  return (
    <aside className="right-panel">
      <button className="action-button">Create Post</button>
      <button className="action-button">Add New Learning Plan</button>
    </aside>
  );
}

export default RightPanel;
