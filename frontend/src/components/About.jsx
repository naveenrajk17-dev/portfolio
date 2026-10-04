import "./About.css";

function About({ text, location, status }) {
  return (
    <aside className="about">
      <h3>About</h3>
      <p className="about-text">{text}</p>
      <hr />
      <p className="about-line">📍 {location}</p>
      <p className="about-line">💼 {status}</p>
    </aside>
  );
}

export default About;