import {Container, Divider} from "@mui/material";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Workspaces from "../components/Workspaces.tsx";
import {useState} from "react";
import FormDialog from "../components/mui/CreateWorkspaceDialog.tsx";
import MainResponsiveAppBar from "../components/mui/MainResponsiveAppBar.tsx";

export default function HomePage() {

    const [open, setOpen] = useState(false);

    return (
        <Container sx={{display: "flex", flexDirection: "column"}}>
            <MainResponsiveAppBar />
            <Box
                sx={{
                    m:2,
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "flex-end",
                    flexWrap: "wrap"}}
            >
                <Typography variant={"h5"}>Your Workspaces</Typography>
                <Button onClick={()=>setOpen(true)} variant="contained">Add a Workspace</Button>

                {open && <FormDialog open={open} handleClose={() => setOpen(false)}/>}
            </Box>

            <Divider orientation="horizontal" variant="middle" />

            <Workspaces />
        </Container>
    )
}
