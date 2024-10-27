import Box from "@mui/material/Box";
import WorkspacePaper from "./ui/WorkspacePaper.tsx";
import {useQuery} from "@tanstack/react-query";
import {Project} from "../types.ts"
import {getProjects} from "../services/api/projectApi.ts";
import Typography from "@mui/material/Typography";

export default function Workspaces() {
    const { data, error, isSuccess } = useQuery<Project[], Error>({
        queryKey: ['projects'],
        queryFn: getProjects
    });

    return (
        <Box
            sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center"
            }}
        >
            {isSuccess && data.map(project => <WorkspacePaper key={project.id} name={project.name} />)}
            {error && <Typography variant={"h6"}>An Error Occurred...</Typography>}
        </Box>
    );
}
