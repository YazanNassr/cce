import CodeEditor from "../components/CodeEditor.tsx";
import Grid from "@mui/material/Grid2";
import RunAppBar from "../components/mui/RunResponsiveAppBar.tsx"
import TextArea from "../components/ui/TextArea.tsx";
import {Workspace} from "../types/types.ts";
import {WorkspaceContext} from "../hooks/WorkspaceContext.tsx";
import {useState} from "react";
import MainResponsiveAppBar from "../components/mui/MainResponsiveAppBar.tsx";

export default function EditorPage({w} : {w: Workspace}) {
    const [workspace, setWorkspace] = useState<Workspace>(w);

    const setInputText = (newVal: string) => setWorkspace({...workspace, input: newVal});
    const setOutputText = (newVal: string) => setWorkspace({...workspace, output: newVal});

    return (
        <WorkspaceContext.Provider value={{workspace, setWorkspace}}>
            <MainResponsiveAppBar />
            <Grid
                container
                columns={12}
            >
                <Grid size={12} >
                    <RunAppBar />
                </Grid>

                <Grid size={{ xs: 12, md: 8 }} >
                    <CodeEditor />
                </Grid>

                <Grid
                    size={{xs: 12, md: 4}}
                    height={{xs: 50, md: 20}}
                >
                    <Grid size={{ xs: 12, md: 4 }} >
                        <TextArea
                            text={workspace.input}
                            setText={setInputText}
                            title={"Input"}
                            readonly={false}
                        />
                    </Grid>

                    <Grid size={{ xs: 12, md: 4 }} >
                        <TextArea
                            text={workspace.output}
                            setText={setOutputText}
                            title={"Output"}
                            readonly={true}
                        />
                    </Grid>
                </Grid>
            </Grid>
        </WorkspaceContext.Provider>
    )
}