export type User = {
    id?: string;
    name: string;
    role: string;
}

export type File = {
    parentPath: string;
    fileName: string;
    sourceCode: string;
}

export type Project = {
    id: string;
    name: string;
    metadataId: string;
    files: File[];
}

export type Workspace = {
    project: Project;
    activeFile: File;
}

export type WorkspaceState = {
    workspace: Workspace;
    setWorkspace: React.Dispatch<React.SetStateAction<Workspace>>;
}

