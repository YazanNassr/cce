import axios, {AxiosRequestConfig} from "axios";
import { Project } from "../../types/types.ts"

const remoteBaseUrl = () => `${import.meta.env.VITE_API_URL}/project`;

const getAxiosConfig = (): AxiosRequestConfig => {
    const token = sessionStorage.getItem("jwt");
    return {
        responseType: "json",
        headers: {
            "Authorization": token,
            "Content-Type": "application/json"
        }
    }
}

export async function getProjects() : Promise<Project[]> {
    const response = await axios.get(`${remoteBaseUrl()}/all`, getAxiosConfig())
    return response.data;
}

export async function getProject(projectId : string) : Promise<Project> {
    const response = await axios.get(`${remoteBaseUrl()}/${projectId}`, getAxiosConfig())
    return response.data;
}

export async function deleteProject(projectId: string) : Promise<void> {
    await axios.delete(`${remoteBaseUrl()}/${projectId}`, getAxiosConfig());
}

export async function createProject(project: Project) : Promise<Project> {
    const response = await axios.post(`${remoteBaseUrl()}`, project, getAxiosConfig());
    return response.data;
}

export async function runProject(projectId: string, input: string, mainFilePath: string) : Promise<string> {
    const response = await axios.post(`${remoteBaseUrl()}/${projectId}/run`, {
        mainFilePath, input
    }, getAxiosConfig());
    return response.data;
}