import "./Header.css";

function Header({ name, role, email, github, theme, onToggleTheme }) {
  return (
    <header className="header">
      <img className="header-photo" src="/images/profile.png" alt={name} />

      <div>
        <p className="header-role">{role}</p>
        <h1 className="header-name">{name}</h1>

        <div className="header-actions">
          <a className="chip" href={`mailto:${email}`}>
            ✉ {email}
          </a>
          <a className="chip" href={github} target="_blank" rel="noreferrer">
            GitHub
          </a>
          <a
            className="chip"
            href="/NaveenRaj_Resume.pdf"
            target="_blank"
            rel="noreferrer"
          >
            👁 View Resume
          </a>
          <a
            className="chip chip-primary"
            href="/NaveenRaj_Resume.pdf"
            download="NaveenRaj_Resume.pdf"
          >
            ⬇ Download Resume
          </a>
        </div>
      </div>

      <button className="theme-toggle" onClick={onToggleTheme}>
        {theme === "dark" ? "☀" : "☾"}
      </button>
    </header>
  );
}

export default Header;