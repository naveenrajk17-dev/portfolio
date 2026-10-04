import "./Tabs.css";

function Tabs({ tabs, active, onChange }) {
  return (
    <nav className="tabs">
      {tabs.map((tab) => (
        <button
          key={tab}
          className={tab === active ? "tab tab-active" : "tab"}
          onClick={() => onChange(tab)}
        >
          {tab}
        </button>
      ))}
    </nav>
  );
}

export default Tabs;