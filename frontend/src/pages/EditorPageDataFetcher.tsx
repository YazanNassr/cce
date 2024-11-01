import {Project, Workspace} from "../types/types.ts";
import {getProject} from "../services/api/projectApi.ts";
import Typography from "@mui/material/Typography";
import {useParams} from "react-router-dom";
import {useQuery} from "@tanstack/react-query";
import EditorPage from "./EditorPage.tsx";

export default function EditorPageDataFetcher() {
    let { projectId } = useParams();

    projectId = projectId ? projectId : "";

    const {data, error, isSuccess} = useQuery<Project, Error>({
        queryKey: [`project@${projectId}`],
        queryFn: () => getProject(projectId)
    })

    if (error) return <Typography variant={"h3"}>An Error Has Occurred!!</Typography>;
    if (!isSuccess) return <Typography variant={"h3"}>Loading...</Typography>;

    if (data) {
        const workspace: Workspace = {
            "project": data,
            "activeFile": data.files[0],
            "input": "",
            "output": ""
        }

        return <EditorPage w={workspace}/>
    }
}