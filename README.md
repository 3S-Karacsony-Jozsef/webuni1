# 3s-react-frontend

react frontend app created by interns

### Tech stack

- React Router 7
- TailwindCSS / ... (optional, vanilla CSS is also fine, explain why)
- Zod
- Zustand
- Vite
- TypeScript
- Storybook (optional)
- Vitest / Jest / ... (choose one, tell us why)

### What to do

Refactor the Telco app. Add missing features, use reusable components, etc.

### Features

To be defined.

## 3.1 Branching Model

We use **feature branching with a mandatory `develop` branch**.

### Main branches

- `main`  
  Represents the released, production-ready state.  
  Only tested, reviewed, and approved changes are merged here.

- `develop`  
  Main integration branch for ongoing development.  
  All feature and bugfix branches must target `develop`.

**Direct commits to `main` and `develop` are not allowed.**  
All changes must go through Pull Requests.

### Working branches

- `feature/<ticket-number>-short-description`  
  Used for new functionality.  
  Created from `develop` and merged back via Pull Request.

- `bugfix/<ticket-number>-short-description`  
  Used for bug fixes.  
  Created from `develop` and merged back via Pull Request.

Branch names must be short, readable, and traceable to a ticket or task.

Main workflow:

```
develop → feature / bugfix → Pull Request → develop → release → main
```

## 3.2 Pull Request Workflow

All changes must go through a Pull Request.

Rules:

- direct pushes to `main` and `develop` are not allowed
- every PR must have **at least one approving reviewer**
- the author cannot approve their own PR
- PR description must clearly explain:
  - what changed
  - why it changed

Small, focused PRs are preferred over large ones.

## 3.3 Commit Message Standards

We use **Conventional Commits**.

Format:

```
<type>: short description
```

Allowed types:

- `feat` - new functionality
- `fix` - bug fix
- `refactor` - code improvement without behavior change
- `docs` - documentation changes
- `test` - tests only
- `chore` - dependencies, build, infrastructure

Examples:

```
feat: add user login endpoint
fix: handle null email in user service
docs: update API authentication section
```

### Commit Frequency

- commit **often** and in small logical steps
- commits should represent working increments
- developers are expected to push their changes at least once per day, ideally at the end of the workday
- work should not remain only on a local machine for extended periods

Frequent commits make reviews easier and reduce risk.

## 3.4 Review Expectations

Code reviews are mandatory and part of shared ownership.

Expectations:

- review for correctness, readability, and consistency
- ask questions if anything is unclear
- reviewers must pull the branch and run the changes locally
- approval means the reviewer understands the code and verified it works
- authors must address feedback constructively

Approval is a responsibility, not a formality.  
Reviews exist to improve quality and protect the codebase.
