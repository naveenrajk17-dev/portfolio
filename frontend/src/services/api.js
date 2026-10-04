const API_BASE =
  import.meta.env.VITE_API_URL || "http://localhost:8081/api";

async function get(path) {
  const response = await fetch(`${API_BASE}${path}`);

  if (!response.ok) {
    throw new Error(`Request failed: ${response.status}`);
  }

  return response.json();
}

async function post(path, body) {
  const response = await fetch(`${API_BASE}${path}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });

  if (!response.ok) {
    throw new Error(`Request failed: ${response.status}`);
  }

  return response.json();
}

export const getProjects = () => get("/projects");
export const getSkills = () => get("/skills");
export const getCertificates = () => get("/certificates");
export const getExperience = () => get("/experience");
export const getEducation = () => get("/education");

export const sendContact = (data) => post("/contact", data);