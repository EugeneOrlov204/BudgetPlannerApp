# Workflow Process

## Introduction to Scrumban

Scrumban is a management framework that combines elements of Scrum and Kanban, aiming to provide the flexibility of Kanban with the structure of Scrum. This approach is particularly suited to teams looking for an adaptive workflow that can accommodate varying workloads and priorities. Given our team's unique working hours, Scrumban allows us to maintain productivity without the strict time commitments required by traditional Scrum.

## Kanban Board Setup

Our project management will utilize a Kanban board with the following columns to track the progress of tasks:

1. **Backlog**: Contains all planned work items that are not yet in progress. Items in this column are prioritized; the most important tasks are moved to "In Progress" as capacity allows.
2. **In Progress**: For tasks currently being worked on. Limiting the number of tasks in this column helps prevent overcommitment and ensures focus.
3. **In Review**: Once a task is completed, it moves to this column, where it awaits peer review. This step ensures quality and adherence to project standards before merging.
4. **Closed**: Tasks that have been reviewed, approved, and completed are moved here, signifying their completion.

### Tracking Time

Given our reduced work hours, it's crucial to manage and track time efficiently. Each team member is encouraged to log their time spent on tasks to help with future planning and workload distribution. This tracking will be informal, focusing more on understanding task complexity and team capacity rather than strict accountability.

## Scrumban Meetings

Daily Scrumban meetings will be held five times a week. These short, stand-up meetings are designed to:
- Provide updates on current tasks.
- Identify any blockers or challenges.
- Adjust priorities and task assignments as needed.

Given our limited working hours, these meetings are crucial for maintaining communication and ensuring everyone is aligned and aware of the project's status and immediate priorities.

## Review Process and Definition of Done

To ensure high-quality outcomes and consistent progress, we establish a clear review process and definition of done:

1. **Feature Completion**: The task or feature is fully implemented and functioning as expected.
2. **Test Coverage**: The code related to the task is adequately covered with unit tests, ensuring reliability and reducing bugs.
3. **Documentation**: All new code is well-documented, providing clarity on its purpose and usage for future reference.
4. **Code Pushed to GitHub**: Changes are pushed to a branch on GitHub, ready for review. To streamline this process:
    - **Task ID in Commits**: Include the task ID and a brief description of the work done in each commit message, formatted as `TASK_ID: WORK_DONE_IN_THE_COMMIT`. This practice helps link commits to specific tasks, making it easier to track changes and review history.
    - **Updating the Target Branch**: Regularly update the target branch (e.g., `master` or `develop`) and merge it into your current working branch. This habit ensures that your branch is up-to-date and helps prevent merge conflicts.
    - **Resolving Merge Conflicts**: Before pushing your changes, ensure that any merge conflicts with the target branch are resolved. This step reduces integration issues and streamlines the review process.
5. **Peer Reviews**: At least two team members must review and approve the changes. This process ensures code quality and fosters team collaboration.
6. **Merge and Close**: Once approved, the changes are merged into the main branch, and the task is moved to the "Closed" column on the Kanban board.

This definition of done serves as a comprehensive checklist that ensures every task meets our project's standards for quality, reliability, and teamwork.

## Conclusion

Adopting a Scrumban approach allows us to blend the structured aspects of Scrum with the flexibility of Kanban, accommodating our unique team dynamics and working hours. By adhering to the outlined workflow process, Kanban board setup, and Scrumban meetings, along with our review process and definition of done, we aim to maintain a productive, adaptable, and cohesive team environment. This framework not only facilitates project progress but also supports the professional growth of our team members by instilling best practices in task management, collaboration, and code quality.