# Quarkus Serverless Started 

这是一个使用 [Quarkus](https://quarkus.io/) 构建的最小化 CRUD 服务示例项目，展示了如何使用 Quarkus 框架构建高性能的 REST API 服务，并与 Hibernate ORM 进行集成。

## 什么是 Quarkus？

Quarkus 是一个为 OpenJDK HotSpot 和 GraalVM 量身定制的 Kubernetes Native Java 框架，基于同类最佳的 Java 库和标准制作而成。它被誉为"超音速亚原子的 Java"（Supersonic Subatomic Java），专门针对容器优先和云原生场景进行了优化。

### Quarkus vs Spring Boot

与传统的 Spring Boot 框架相比，Quarkus 在多个方面具有显著优势：

1. **启动速度**：Quarkus 应用启动速度比 Spring Boot 快 10-80 倍
   - Spring Boot 应用通常需要几秒到十几秒启动
   - Quarkus JVM 模式启动时间在 100-500ms 之间
   - Quarkus Native 模式启动时间仅需 10-50ms

2. **内存占用**：Quarkus 内存占用比 Spring Boot 少 70-90%
   - Spring Boot 应用通常需要 200-500MB 内存
   - Quarkus JVM 模式仅需 50-100MB
   - Quarkus Native 模式仅需 25-50MB

3. **工件大小**：Quarkus 生成的工件比 Spring Boot 小 3-10 倍
   - Spring Boot 应用通常有 50-100MB 的 JAR 文件
   - Quarkus JVM 模式生成的 JAR 文件通常只有几 MB
   - Quarkus Native 模式生成的可执行文件约为 20-50MB

这些优势使 Quarkus 成为容器化部署、微服务架构和 Serverless 场景的理想选择。

### 什么是 Quarkus Native？

Quarkus Native 是 Quarkus 框架的一项核心功能，它利用 GraalVM 的 native-image 技术将 Java 应用编译为原生可执行文件。这种原生编译过程在构建时完成，而不是在运行时进行，从而消除了传统 Java 应用的许多运行时开销。

#### Java Native 概念

Java Native 指的是使用 GraalVM 等工具将 Java 字节码提前编译（Ahead-of-Time, AOT）为机器码的技术。与传统的即时编译（Just-In-Time, JIT）不同，原生编译在应用运行之前就完成了大部分优化工作，包括：

- 类加载分析和优化
- 反射调用的预先解析
- 死代码消除
- 内存布局优化

#### Native 模式性能对比

| 指标 | Spring Boot | Quarkus JVM | Quarkus Native |
|------|-------------|-------------|----------------|
| 启动时间 | 3000-5000ms | 200-500ms | 10-50ms |
| 内存占用 | 300-500MB | 80-150MB | 25-50MB |
| 响应时间 | 基准 | 基准 | 基准 |
| 镜像大小 | 50-100MB | 5-20MB | 20-50MB |

## 项目概述

本项目演示了以下功能：
- 使用 Quarkus 构建 RESTful API
- 集成 Hibernate ORM with Panache 实现数据持久化
- 实现基本的 CRUD 操作
- 展示 Quarkus 的本地可执行文件构建能力

通过这个项目，你可以亲身体验 Quarkus 框架的快速开发模式和卓越性能，特别是 Quarkus Native 带来的极致启动速度和内存效率。无论你是想学习现代云原生 Java 开发，还是希望提升应用性能和资源利用率，这个项目都是一个绝佳的起点。

下图是通过 Quarkus native可执行文件启动后的速度，实测速度如下 83 ms
![img.png](img.png)

## 快速开始

## 数据库配置

项目默认使用 MySQL 数据库，配置位于 `src/main/resources/application.properties` 文件中：

```properties
quarkus.datasource.db-kind=mysql
quarkus.datasource.username=
quarkus.datasource.password=
quarkus.datasource.jdbc.url=jdbc:mysql://192.168.0.1:3306/yudao?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true&rewriteBatchedStatements=true
```

可以根据实际环境修改数据库连接配置。


### 运行应用

在开发模式下运行应用：

```bash
mvn quarkus:dev
```

应用将在 `http://localhost:8080` 上启动。

### 测试 API

应用提供了以下 REST API 端点：

- `GET /hello` - 返回 "hello"
- `GET /hello/greeting/{name}` - 返回问候语
- `GET /hello/users` - 获取所有用户
- `GET /hello/users/{id}` - 根据 ID 获取用户
- `POST /hello/users` - 创建新用户
- `PUT /hello/users/{id}` - 更新用户
- `DELETE /hello/users/{id}` - 删除用户

## 本地启动

```
 在 IDE 中 通过ProjectMain.main() 启动应用
```

## 构建本地可执行文件

首先需要进入项目目录：

```bash
cd quarkus-demo
```

Quarkus 支持构建本地可执行文件，可以获得更快的启动速度和更低的资源消耗。

### 使用Maven直接构建

可以通过以下命令构建本地可执行文件：

```bash
mvn install -Pnative
```

> ⚠️ **注意**：直接使用Maven构建需要本地安装配置GraalVM及相关依赖，如果本地环境缺少相关依赖或配置不正确，容易导致构建失败。特别是在不同操作系统上，可能需要安装特定的构建工具和库。
>
> 🪟 **Windows系统说明**：在Windows系统上使用Maven直接构建会生成Windows平台的可执行文件（.exe），但需要安装GraalVM for Windows以及Visual Studio Build Tools或Visual Studio 2022。

### 使用Docker镜像构建（推荐）

为了确保构建环境的一致性，推荐使用Quarkus官方提供的Docker镜像进行构建。可以使用以下命令指定构建镜像：

```bash
mvn install -Pnative -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-native-image:22.3-java17
```

> ℹ️ **说明**：Docker镜像构建是指在本地通过Docker启动一个专门用于构建Quarkus Native应用的容器环境，在该容器内完成Java代码到本地可执行文件的编译过程。构建完成后，生成的可执行文件会输出到本地的`target`目录中，而不是在Docker容器中运行服务。这种方式避免了本地环境配置的复杂性，确保构建过程的一致性和可重复性。
>
> 🐧 **平台限制**：通过Docker镜像构建只能生成Linux平台的可执行文件，即使在Windows或macOS上执行该命令也是如此。如果需要构建Windows可执行文件，必须在Windows环境下使用Maven直接构建。

这种方式会在Docker内部完成本地可执行文件的构建，并直接打包成最小化的镜像。

### 运行构建出的可执行文件

构建完成后，可以在 `target` 目录下找到生成的可执行文件。根据构建方式和平台不同，可执行文件的名称和格式也不同：

- **Maven直接构建（Windows）**：[target\getting-started-1.0.0-SNAPSHOT-runner.exe](file://D:\project\IdeaProjects\personal\quarkus-demo\target\getting-started-1.0.0-SNAPSHOT-runner.exe)
- **Docker镜像构建（所有平台）**：[target/getting-started-1.0.0-SNAPSHOT-runner](file://D:\project\IdeaProjects\personal\quarkus-demo\target/getting-started-1.0.0-SNAPSHOT-runner)

可执行文件可以直接运行，无需安装 JVM，具有更快的启动速度和更低的内存占用：

```bash
# Linux/macOS (Docker镜像构建结果)
./target/getting-started-1.0.0-SNAPSHOT-runner

# Windows (Maven直接构建结果)
target\getting-started-1.0.0-SNAPSHOT-runner.exe
```

## 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── org/acme/getting/started/
│   │       ├── User.java              # 用户实体类
│   │       ├── UserRepository.java     # 用户数据访问类
│   │       ├── UserService.java        # 用户服务类
│   │       ├── GreetingService.java    # 问候服务类
│   │       ├── GreetingController.java   # REST 资源类
│   │       └── DbRowSample.java       # 数据库操作示例
│   └── resources/
│       ├── application.properties      # 应用配置文件
│       └── create-table.sql           # 建表语句
└── test/
    └── java/
        └── org/acme/getting/started/
            └──  GreetingResourceTest.java  # 单元测试
```

## Quarkus与云原生应用

以上就是Quarkus的入门示例和部署构建的过程。通过体验Quarkus native的可执行文件启动，您是否感受到了极速的启动速度？这种卓越的性能使Quarkus成为云原生应用开发的理想选择。

### Quarkus云原生优势

Quarkus专为云原生环境而设计，具有以下显著优势：

1. **超快启动速度**：Native模式下启动时间仅需10-50毫秒，比传统Java框架快10-80倍
2. **极低内存占用**：运行时内存仅需10-50MB，比传统应用节省70-90%的内存
3. **小工件体积**：生成的可执行文件体积小3-10倍，减少网络传输和存储开销
4. **容器优化**：为Docker和Kubernetes环境进行了深度优化，资源利用率更高

### Serverless架构介绍

Serverless（无服务器）是一种云计算执行模型，其中云提供商动态管理机器资源的分配和供应。其核心特点包括：

1. **事件驱动**：应用通过事件触发执行，无需持续运行
2. **自动扩缩容**：根据请求量自动调整资源，无需人工干预
3. **按需计费**：只为实际执行的代码付费，不执行时不产生费用
4. **免运维**：无需管理服务器、操作系统或运行时环境

#### 按需计费详解

Serverless最吸引人的特性之一是其精细化的按需计费模式。这种计费方式具有以下特点：

- **请求级计费**：根据实际处理的请求计费，而不是按照预分配的资源计费
- **执行时间计费**：只对函数实际运行的时间进行计费，精确到毫秒级
- **零请求零费用**：即使服务已部署在线上，没有请求时不会产生任何费用
- **资源弹性**：无需为峰值负载预置资源，避免资源闲置浪费

这种计费模式**能极大节省企业成本和服务器资源**，特别体现在：

1. **成本优化**：企业只需为实际使用的计算资源付费，相比传统服务器预付费模式可节省高达80%的成本
2. **资源利用率提升**：避免了传统部署模式下服务器资源闲置的问题，资源利用率接近100%
3. **运维成本降低**：无需专业的运维团队维护服务器，降低人力成本
4. **弹性扩容无成本风险**：自动扩缩容机制使企业无需担心流量突增带来的资源预置风险

这种计费模式特别适合以下场景：

1. **突发流量应用**：如活动推广、促销秒杀等临时性高并发场景
2. **低频业务处理**：如定时任务、数据处理、报表生成等周期性或偶发性任务
3. **事件驱动处理**：如文件上传处理、消息队列处理、IoT设备数据处理等
4. **微服务拆分**：将大型应用拆分为多个小型函数，独立部署和计费
5. **开发测试环境**：避免为不常使用的测试环境支付固定费用

#### 主流云厂商Serverless服务

目前主流云服务提供商均已支持Serverless功能，各自的产品命名如下：

- **阿里云**：[函数计算（Function Compute）](https://www.alibabacloud.com/product/function-compute)
- **亚马逊AWS**：[AWS Lambda](https://aws.amazon.com/lambda/)
- **华为云**：[FunctionGraph](https://www.huaweicloud.com/product/functiongraph.html)
- **腾讯云**：[云函数（Serverless Cloud Function，SCF）](https://cloud.tencent.com/product/scf)

这些平台都提供了完整的Serverless解决方案，支持多种编程语言，具备自动扩缩容、按需计费等核心特性。

### Serverless的挑战与限制

尽管Serverless具有诸多优势，但也存在一些挑战和限制：

1. **冷启动问题**：传统框架（如Spring Boot）应用启动速度较慢，在Serverless环境中可能导致用户明显的等待时间，影响用户体验
2. **调试复杂性**：分布式架构使问题排查和调试变得更加困难
3. **供应商锁定**：不同云厂商的Serverless平台存在差异，迁移成本较高
4. **状态管理限制**：函数无状态特性使有状态应用的开发变得复杂

特别是**冷启动问题**，对于使用传统Java框架（如Spring Boot）构建的应用尤为明显。Spring Boot应用通常需要几秒甚至十几秒才能启动完成，这在Serverless环境中会严重影响用户体验，特别是在函数需要频繁冷启动的场景下。

### Quarkus与Serverless的完美结合

Quarkus的特性与Serverless架构的需求高度契合，结合后具有以下优势：

1. **极致冷启动性能**：Quarkus的快速启动能力显著减少Serverless函数的冷启动时间
2. **资源高效利用**：低内存占用和小工件体积使单个容器能承载更多函数实例
3. **成本大幅降低**：
   - 减少服务器资源需求，节省硬件成本
   - 更快的响应速度降低计算时间，减少计费时长
   - 更小的镜像尺寸减少存储和网络传输成本
4. **弹性伸缩优化**：快速启动能力使应用能更快响应流量峰值，提升用户体验

通过Quarkus构建的Serverless应用，能够极大节省资源和服务器成本，特别适合需要快速响应、弹性扩缩容的现代云原生应用场景。Quarkus凭借其毫秒级的启动速度，完美弥补了传统框架在Serverless环境中的性能短板，实现了**既极大节省企业成本又不牺牲用户体验**的双重优势。

## Serverless在线体验

您可以通过以下地址体验部署在Serverless平台上的本项目服务：

**体验地址**：[quarkus-demo.hongdux.com](https://quarkus-demo.hongdux.com/hello/users)

### API接口测试示例

本项目提供了丰富的REST API接口，您可以通过以下示例进行测试：

1. **获取问候语**
   ```bash
   curl https://quarkus-demo.hongdux.com/hello
   ```

2. **根据姓名获取个性化问候**
   ```bash
   curl https://quarkus-demo.hongdux.com/hello/greeting/Quarkus
   ```

3. **获取所有用户**
   ```bash
   curl https://quarkus-demo.hongdux.com/hello/users
   ```

4. **根据ID获取用户**
   ```bash
   curl https://quarkus-demo.hongdux.com/hello/users/1
   ```

5. **创建新用户**
   ```bash
   curl -X POST https://quarkus-demo.hongdux.com/hello/users \
        -H "Content-Type: application/json" \
        -d '{"username": "testuser", "email": "test@example.com", "fullName": "Test User", "age": 25}'
   ```

6. **更新用户信息**
   ```bash
   curl -X PUT https://quarkus-demo.hongdux.com/hello/users/1 \
        -H "Content-Type: application/json" \
        -d '{"username": "updateduser", "email": "updated@example.com", "fullName": "Updated User", "age": 30}'
   ```

7. **删除用户**
   ```bash
   curl -X DELETE http://quarkus-demo.hongdux.com/hello/users/1
   ```

通过这些API接口，您可以充分体验Quarkus在Serverless环境中的卓越性能表现，感受毫秒级的响应速度和极低的资源消耗。

## 许可证

本项目使用 [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0) 许可证。

## 专业服务支持

如果您希望将现有服务升级到 Quarkus 框架或迁移到 Serverless 架构，我们提供一站式解决方案：

### 我们的服务包括：

- **架构咨询**：根据您的业务需求提供最适合的技术架构建议
- **Quarkus 迁移**：将传统 Java 应用平滑迁移到 Quarkus 框架，提升性能和资源利用率
- **Serverless 转型**：帮助您将应用部署到 Serverless 平台，实现极致的弹性伸缩和成本优化
- **性能优化**：针对 Quarkus 应用进行深度优化，充分发挥其启动速度快、内存占用低的优势
- **全周期支持**：从架构设计、开发实施到上线运维的全流程技术支持

### 为什么选择我们？

✅ **技术专家团队**：拥有丰富的 Quarkus 和 Serverless 实战经验  
✅ **成本优化方案**：帮助企业降低 60%+ 的服务器成本  
✅ **性能提升保障**：实现 10-80 倍的启动速度提升  
✅ **无缝迁移服务**：最大程度降低业务迁移风险  
✅ **持续技术支持**：提供长期的技术支持和咨询服务  

### 联系我们

想要了解如何通过 Quarkus 和 Serverless 技术为您的企业降本增效？

📧 邮箱：[zhujunjie@hongdux.com](mailto:service@hongdux.com)  
🌐 官网：[www.hongdux.com](http://www.hongdux.com)  
📱 微信：hongdux

让我们帮助您的应用实现性能和成本的双重优化！
