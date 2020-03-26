### Delete branch from upstream
`git push origin --delete <branch_name>`

### Delete branch from local
`git branch -d <branch_name>`

### Create and track branch from an existing remote branch
`git branch --track <branch_name_to_create> origin/<remote_branch_name>`

### Push branch to upstream
`git push origin <branch_name>`

### Revert the last local commit
`git reset --soft HEAD~1`

### Unstage the files
`git reset HEAD <files_to_unsage> OR .`

### Merge master into another branch. Once merged resolve the conflicts
`git checkout master`
`git pull`
`git checkout <branch_name>`
`git merge master`

### Hard reset branch from remote
`git reset --hard origin/<branch_name>`

### Squish commits, let's say last 5 commits.
`git rebase -i HEAD~<NUMBER OF COMMITS>`

`git rebase -i HEAD~5`

`git push origin <branch_name> --force # If force doesn't work then delete the remote branch and push again`

### View the content of an arbitrary stash
`git stash show -p stash@{1}`


### Remove the last commit from local as well as remote
`git reset HEAD^`
`git push origin +HEAD`

### See last commit, get commit hash, revert it.
`git log -n 1`
`git revert <commit_hash>`
`git push origin <branch_name>`

### Rename a local and remote branch in git
#### If you have named a branch incorrectly and pushed this to remote repository follow these steps to rename it.

- Rename local branch
    If you are on the branch you want to rename
   - `git branch -m <new_branch_name>`
   
     If you are on a different branch
   - `git branch -m <old_branch_name> <new_branch_name>`
- Delete the <old_branch_name> remote branch and push the <new_branch_name> local branch
  - `git push origin :<old_branch_name> <new_branch_name>`
- Reset the upstream branch for the <new_branch_name> local branch
    Switch to the branch and then execute below command
   - `git push origin -u <new_branch_name>`
