/**
 * Copyright (c) 2022-2023, Mybatis-Flex (fuhai999@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.acme.getting.started;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DbRowSample {
    @Inject
    private UserService userService;

    public void testJpa(){
        System.out.println("=== User CRUD Operations Demo ===");
        
        // 查询所有用户
        System.out.println("All users: " + userService.getAllUsers());

        // 新增1条数据
        User newUser = new User();
        newUser.setUsername("lisi");
        newUser.setEmail("lisi@example.com");
        newUser.setFullName("Li Si");
        newUser.setAge(28);
        newUser.setActive(true);
        userService.createUser(newUser);
        System.out.println("New user ID: " + newUser.id);

        // 根据用户名查询用户
        User user = userService.getUserByUsername("lisi");
        System.out.println("Found user: " + user);
        
        // 查询活跃用户
        List<User> activeUsers = userService.getActiveUsers();
        System.out.println("Active users count: " + activeUsers.size());
        
        // 查询非活跃用户
        List<User> inactiveUsers = userService.getInactiveUsers();
        System.out.println("Inactive users count: " + inactiveUsers.size());
        
        // 根据姓名模糊查询
        List<User> usersWithName = userService.getUsersByName("John");
        System.out.println("Users with 'John' in name: " + usersWithName.size());
        
        // 更新用户
        User updatedUser = new User();
        updatedUser.setUsername("lisi_updated");
        updatedUser.setEmail("lisi_updated@example.com");
        updatedUser.setFullName("Li Si Updated");
        updatedUser.setAge(29);
        updatedUser.setActive(true);
        userService.updateUser(newUser.id, updatedUser);
        System.out.println("Updated user: " + userService.getUserById(newUser.id));
        
        // 删除用户
        boolean deleted = userService.deleteUser(newUser.id);
        System.out.println("User deleted: " + deleted);
        
        // 再次查询所有用户
        System.out.println("All users after operations: " + userService.getAllUsers().size());
    }
}