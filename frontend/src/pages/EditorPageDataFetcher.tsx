import EditorPage from "./EditorPage.tsx";
import {useQuery} from "@tanstack/react-query";
import {Project, Workspace} from "../types.ts";
import {getProjects} from "../services/api/projectApi.ts";
import Typography from "@mui/material/Typography";

export default function EditorPageDataFetcher() {
    const {data, error, isSuccess} = useQuery<Project[], Error>({
        queryKey: ['projects'],
        queryFn: getProjects
    })


    if (error) return <Typography variant={"h3"}>An Error Has Occurred!!</Typography>;
    if (!isSuccess) return <Typography variant={"h3"}>Loading...</Typography>;
    if (data) {
        const workspace: Workspace = {
            project: data[0],
            activeFile: data[0].files[0]
        }

        return <EditorPage w={workspace}/>
    }
}