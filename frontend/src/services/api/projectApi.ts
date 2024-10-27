import axios from "axios";
import { Project } from "../../types.ts"

export async function getProjects() : Promise<Project[]> {
    return [
        {
            id: "1",
            name: "awesome project",
            metadataId: "123",
            files: [{
                parentPath: "src",
                fileName: "main.py",
                sourceCode: "print('Hello, from main!')"
            }, {
                parentPath: "src",
                fileName: "util.py",
                sourceCode: "print('Hello, from util!')"

            }]
        },
        {
            id: "2",
            name: "fantastic project",
            metadataId: "123",
            files: []
        }
    ];

    const response = await axios.get(
        `${import.meta.env.VITE_API_URL}/projects`, {responseType: "json"});
    return response.data;
}

export async function deleteProject(project: Project) : Promise<void> {
    await axios.delete(`${import.meta.env.VITE_API_URL}/projects/${project.id}`, {responseType: "json"});
}

export async function createProject(project: Project) : Promise<Project> {
    const response = await axios.post(
        `${import.meta.env.VITE_API_URL}/users`, project, {
            headers: { contentType: "application/json"},
            responseType: "json"
        });

    return response.data;
}
