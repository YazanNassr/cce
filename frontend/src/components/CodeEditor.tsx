import Editor from '@monaco-editor/react'
import {useWorkspaceContext} from "../WorkspaceContext.tsx";

export default function CodeEditor() {
    const {workspace} = useWorkspaceContext()

    return <Editor
        height="80vh"
        defaultLanguage="python"
        theme="vs-dark"
        defaultValue={workspace.activeFile.sourceCode}
    />;
}