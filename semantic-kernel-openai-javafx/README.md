# Semantic Kernel + simple JavaFX GUI
## How to run
Provide OpenAI API key:
```shell
export OPENAI_API_KEY=<your_api_key>
```
Navigate to project directory and run the app
```shell
../gradlew run
```
## How to use GUI
- right side panel corresponds to the state of `Person` table in the database
- left side panel contains chat history
- bottom text field allows to enter a command for ChatGPT
- type the command and click **Send** button

### Available functions
Chat should react to instructions telling to:
- list all persons
- insert a new person
- find info for particular person by name

## Known issues
Sometimes requesting the chat to insert a new person results in a death of JVM on Mac with M1 processor - error details in the summary below.
<summary>
JVM death
<details>

```shell

#
# A fatal error has been detected by the Java Runtime Environment:
#
#  SIGBUS (0xa) at pc=0x00000001125d4d94, pid=40792, tid=81975
#
# JRE version: OpenJDK Runtime Environment Homebrew (22.0) (build 22)
# Java VM: OpenJDK 64-Bit Server VM Homebrew (22, mixed mode, sharing, tiered, compressed oops, compressed class ptrs, g1 gc, bsd-aarch64)
# Problematic frame:
# j  com.sun.glass.ui.mac.MacAccessible.NSAccessibilityPostNotification(JJ)V+0 javafx.graphics@22.0.1
#
# No core dump will be written. Core dumps have been disabled. To enable core dumping, try "ulimit -c unlimited" before starting Java again
#
# If you would like to submit a bug report, please visit:
#   https://github.com/Homebrew/homebrew-core/issues
# The crash happened outside the Java Virtual Machine in native code.
# See problematic frame for where to report the bug.
#

---------------  S U M M A R Y ------------

Command Line: --module-path=/Users/ddzikon/.gradle/caches/modules-2/files-2.1/org.openjfx/javafx-controls/22.0.1/461634308b581283dd704de9f196a6e9ca945f2a/javafx-controls-22.0.1-mac-aarch64.jar:/Users/ddzikon/.gradle/caches/modules-2/files-2.1/org.openjfx/javafx-graphics/22.0.1/e353919d0174b1e4b760103d9ee894bac750e40b/javafx-graphics-22.0.1-mac-aarch64.jar:/Users/ddzikon/.gradle/caches/modules-2/files-2.1/org.openjfx/javafx-base/22.0.1/fd9e52ab57af9a7cefebd71687884c02e7d619a3/javafx-base-22.0.1-mac-aarch64.jar --add-modules=javafx.controls -Dfile.encoding=UTF-8 -Duser.country=PL -Duser.language=en -Duser.variant org.example.Main

Host: "MacBookPro18,1" arm64, 10 cores, 32G, Darwin 23.5.0, macOS 14.5 (23F79)
Time: Tue Jun 18 12:47:40 2024 CEST elapsed time: 35.803638 seconds (0d 0h 0m 35s)

---------------  T H R E A D  ---------------

Current thread (0x0000000138253400):  JavaThread "boundedElastic-1" daemon [_thread_in_native, id=81975, stack(0x0000000175438000,0x000000017563b000) (2060K)]

Stack: [0x0000000175438000,0x000000017563b000],  sp=0x0000000175639250,  free space=2052k
Native frames: (J=compiled Java code, j=interpreted, Vv=VM code, C=native code)
j  com.sun.glass.ui.mac.MacAccessible.NSAccessibilityPostNotification(JJ)V+0 javafx.graphics@22.0.1
j  com.sun.glass.ui.mac.MacAccessible.sendNotification(Ljavafx/scene/AccessibleAttribute;)V+755 javafx.graphics@22.0.1
J 5035 c1 javafx.scene.Node.notifyAccessibleAttributeChanged(Ljavafx/scene/AccessibleAttribute;)V javafx.graphics@22.0.1 (41 bytes) @ 0x000000010b94664c [0x000000010b945ec0+0x000000000000078c]
j  javafx.scene.control.TableView$TableViewFocusModel.lambda$new$0(Ljavafx/scene/control/TableView;Ljavafx/beans/Observable;)V+4 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewFocusModel$$Lambda+0x000002000167b778.invalidated(Ljavafx/beans/Observable;)V+5 javafx.controls@22.0.1
J 5046 c1 com.sun.javafx.binding.ExpressionHelper$Generic.fireValueChangedEvent()V javafx.base@22.0.1 (218 bytes) @ 0x000000010b94a7e8 [0x000000010b94a600+0x00000000000001e8]
J 3901 c1 javafx.beans.property.ReadOnlyObjectWrapper.fireValueChangedEvent()V javafx.base@22.0.1 (19 bytes) @ 0x000000010b6dc1d4 [0x000000010b6dbd80+0x0000000000000454]
J 3565 c1 javafx.beans.property.ObjectPropertyBase.set(Ljava/lang/Object;)V javafx.base@22.0.1 (76 bytes) @ 0x000000010b64bbf4 [0x000000010b64b580+0x0000000000000674]
j  javafx.scene.control.TableView$TableViewFocusModel.setFocusedCell(Ljavafx/scene/control/TablePosition;)V+5 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewFocusModel.focus(ILjavafx/scene/control/TableColumn;)V+17 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewSelectionModel.focus(Ljavafx/scene/control/TablePosition;)V+34 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewSelectionModel.focus(ILjavafx/scene/control/TableColumn;)V+14 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewSelectionModel.focus(I)V+3 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewArrayListSelectionModel.clearSelection()V+30 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewArrayListSelectionModel.updateDefaultSelection()V+36 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewArrayListSelectionModel.lambda$new$2(Ljavafx/collections/ListChangeListener$Change;)V+42 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewArrayListSelectionModel$$Lambda+0x0000020001678bf8.onChanged(Ljavafx/collections/ListChangeListener$Change;)V+5 javafx.controls@22.0.1
j  javafx.collections.WeakListChangeListener.onChanged(Ljavafx/collections/ListChangeListener$Change;)V+17 javafx.base@22.0.1
j  com.sun.javafx.collections.ListListenerHelper$Generic.fireValueChangedEvent(Ljavafx/collections/ListChangeListener$Change;)V+96 javafx.base@22.0.1
J 3506 c1 javafx.collections.ListChangeBuilder.commit()V javafx.base@22.0.1 (516 bytes) @ 0x000000010b6307fc [0x000000010b62fbc0+0x0000000000000c3c]
J 6839 c2 javafx.collections.ListChangeBuilder.endChange()V javafx.base@22.0.1 (32 bytes) @ 0x0000000112e9420c [0x0000000112e941c0+0x000000000000004c]
J 5080 c1 javafx.collections.ModifiableObservableListBase.setAll(Ljava/util/Collection;)Z javafx.base@22.0.1 (47 bytes) @ 0x000000010b96d8a4 [0x000000010b96c740+0x0000000000001164]
j  org.example.db.Repository.refreshGuiDbState()V+7
j  org.example.db.Repository.save(Lorg/example/db/Person;)Lorg/example/db/Person;+44
j  org.example.agent.Plugin.insertNewPerson(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;+20
j  java.lang.invoke.LambdaForm$DMH+0x000002000145c000.invokeVirtual(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;+14 java.base@22
j  java.lang.invoke.LambdaForm$MH+0x0000020001b30400.invoke(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;+67 java.base@22
J 7139 c2 jdk.internal.reflect.DirectMethodHandleAccessor.invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object; java.base@22 (92 bytes) @ 0x0000000112ecc074 [0x0000000112ecbf00+0x0000000000000174]
J 2672 c1 java.lang.reflect.Method.invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object; java.base@22 (108 bytes) @ 0x000000010b553d6c [0x000000010b553800+0x000000000000056c]
j  com.microsoft.semantickernel.semanticfunctions.KernelFunctionFromMethod.lambda$invokeAsyncFunction$4(Ljava/lang/reflect/Method;Ljava/lang/Object;Ljava/util/List;)Ljava/lang/Object;+47
j  com.microsoft.semantickernel.semanticfunctions.KernelFunctionFromMethod$$Lambda+0x0000020001b27a30.call()Ljava/lang/Object;+12
j  reactor.core.publisher.MonoCallable.call()Ljava/lang/Object;+4
j  reactor.core.publisher.FluxFlatMap.trySubscribeScalarMap(Lorg/reactivestreams/Publisher;Lreactor/core/CoreSubscriber;Ljava/util/function/Function;ZZ)Z+11
j  reactor.core.publisher.MonoFlatMap.subscribeOrReturn(Lreactor/core/CoreSubscriber;)Lreactor/core/CoreSubscriber;+11
j  reactor.core.publisher.Mono.subscribe(Lorg/reactivestreams/Subscriber;)V+61
j  reactor.core.publisher.MonoSubscribeOn$SubscribeOnSubscriber.run()V+15
j  reactor.core.scheduler.WorkerTask.call()Ljava/lang/Void;+14
j  reactor.core.scheduler.WorkerTask.call()Ljava/lang/Object;+1
j  java.util.concurrent.FutureTask.run()V+39 java.base@22
j  java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run()V+28 java.base@22
j  java.util.concurrent.ThreadPoolExecutor.runWorker(Ljava/util/concurrent/ThreadPoolExecutor$Worker;)V+92 java.base@22
j  java.util.concurrent.ThreadPoolExecutor$Worker.run()V+5 java.base@22
j  java.lang.Thread.runWith(Ljava/lang/Object;Ljava/lang/Runnable;)V+5 java.base@22
j  java.lang.Thread.run()V+19 java.base@22
v  ~StubRoutines::call_stub 0x00000001125cc154
V  [libjvm.dylib+0x3ca7a4]  JavaCalls::call_helper(JavaValue*, methodHandle const&, JavaCallArguments*, JavaThread*)+0x25c
V  [libjvm.dylib+0x3c9b70]  JavaCalls::call_virtual(JavaValue*, Klass*, Symbol*, Symbol*, JavaCallArguments*, JavaThread*)+0x100
V  [libjvm.dylib+0x3c9c38]  JavaCalls::call_virtual(JavaValue*, Handle, Klass*, Symbol*, Symbol*, JavaThread*)+0x64
V  [libjvm.dylib+0x4748d4]  thread_entry(JavaThread*, JavaThread*)+0x9c
V  [libjvm.dylib+0x3daf94]  JavaThread::thread_main_inner()+0x84
V  [libjvm.dylib+0x3dadf0]  JavaThread::run()+0x150
V  [libjvm.dylib+0x7e9768]  Thread::call_run()+0x8c
V  [libjvm.dylib+0x686954]  thread_native_entry(Thread*)+0xcc
C  [libsystem_pthread.dylib+0x6f94]  _pthread_start+0x88
Java frames: (J=compiled Java code, j=interpreted, Vv=VM code)
j  com.sun.glass.ui.mac.MacAccessible.NSAccessibilityPostNotification(JJ)V+0 javafx.graphics@22.0.1
j  com.sun.glass.ui.mac.MacAccessible.sendNotification(Ljavafx/scene/AccessibleAttribute;)V+755 javafx.graphics@22.0.1
J 5035 c1 javafx.scene.Node.notifyAccessibleAttributeChanged(Ljavafx/scene/AccessibleAttribute;)V javafx.graphics@22.0.1 (41 bytes) @ 0x000000010b94664c [0x000000010b945ec0+0x000000000000078c]
j  javafx.scene.control.TableView$TableViewFocusModel.lambda$new$0(Ljavafx/scene/control/TableView;Ljavafx/beans/Observable;)V+4 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewFocusModel$$Lambda+0x000002000167b778.invalidated(Ljavafx/beans/Observable;)V+5 javafx.controls@22.0.1
J 5046 c1 com.sun.javafx.binding.ExpressionHelper$Generic.fireValueChangedEvent()V javafx.base@22.0.1 (218 bytes) @ 0x000000010b94a7e8 [0x000000010b94a600+0x00000000000001e8]
J 3901 c1 javafx.beans.property.ReadOnlyObjectWrapper.fireValueChangedEvent()V javafx.base@22.0.1 (19 bytes) @ 0x000000010b6dc1d4 [0x000000010b6dbd80+0x0000000000000454]
J 3565 c1 javafx.beans.property.ObjectPropertyBase.set(Ljava/lang/Object;)V javafx.base@22.0.1 (76 bytes) @ 0x000000010b64bbf4 [0x000000010b64b580+0x0000000000000674]
j  javafx.scene.control.TableView$TableViewFocusModel.setFocusedCell(Ljavafx/scene/control/TablePosition;)V+5 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewFocusModel.focus(ILjavafx/scene/control/TableColumn;)V+17 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewSelectionModel.focus(Ljavafx/scene/control/TablePosition;)V+34 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewSelectionModel.focus(ILjavafx/scene/control/TableColumn;)V+14 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewSelectionModel.focus(I)V+3 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewArrayListSelectionModel.clearSelection()V+30 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewArrayListSelectionModel.updateDefaultSelection()V+36 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewArrayListSelectionModel.lambda$new$2(Ljavafx/collections/ListChangeListener$Change;)V+42 javafx.controls@22.0.1
j  javafx.scene.control.TableView$TableViewArrayListSelectionModel$$Lambda+0x0000020001678bf8.onChanged(Ljavafx/collections/ListChangeListener$Change;)V+5 javafx.controls@22.0.1
j  javafx.collections.WeakListChangeListener.onChanged(Ljavafx/collections/ListChangeListener$Change;)V+17 javafx.base@22.0.1
j  com.sun.javafx.collections.ListListenerHelper$Generic.fireValueChangedEvent(Ljavafx/collections/ListChangeListener$Change;)V+96 javafx.base@22.0.1
J 3506 c1 javafx.collections.ListChangeBuilder.commit()V javafx.base@22.0.1 (516 bytes) @ 0x000000010b6307fc [0x000000010b62fbc0+0x0000000000000c3c]
J 6839 c2 javafx.collections.ListChangeBuilder.endChange()V javafx.base@22.0.1 (32 bytes) @ 0x0000000112e9420c [0x0000000112e941c0+0x000000000000004c]
J 5080 c1 javafx.collections.ModifiableObservableListBase.setAll(Ljava/util/Collection;)Z javafx.base@22.0.1 (47 bytes) @ 0x000000010b96d8a4 [0x000000010b96c740+0x0000000000001164]
j  org.example.db.Repository.refreshGuiDbState()V+7
j  org.example.db.Repository.save(Lorg/example/db/Person;)Lorg/example/db/Person;+44
j  org.example.agent.Plugin.insertNewPerson(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;+20
j  java.lang.invoke.LambdaForm$DMH+0x000002000145c000.invokeVirtual(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;+14 java.base@22
j  java.lang.invoke.LambdaForm$MH+0x0000020001b30400.invoke(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;+67 java.base@22
J 7139 c2 jdk.internal.reflect.DirectMethodHandleAccessor.invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object; java.base@22 (92 bytes) @ 0x0000000112ecc074 [0x0000000112ecbf00+0x0000000000000174]
J 2672 c1 java.lang.reflect.Method.invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object; java.base@22 (108 bytes) @ 0x000000010b553d6c [0x000000010b553800+0x000000000000056c]
j  com.microsoft.semantickernel.semanticfunctions.KernelFunctionFromMethod.lambda$invokeAsyncFunction$4(Ljava/lang/reflect/Method;Ljava/lang/Object;Ljava/util/List;)Ljava/lang/Object;+47
j  com.microsoft.semantickernel.semanticfunctions.KernelFunctionFromMethod$$Lambda+0x0000020001b27a30.call()Ljava/lang/Object;+12
j  reactor.core.publisher.MonoCallable.call()Ljava/lang/Object;+4
j  reactor.core.publisher.FluxFlatMap.trySubscribeScalarMap(Lorg/reactivestreams/Publisher;Lreactor/core/CoreSubscriber;Ljava/util/function/Function;ZZ)Z+11
j  reactor.core.publisher.MonoFlatMap.subscribeOrReturn(Lreactor/core/CoreSubscriber;)Lreactor/core/CoreSubscriber;+11
j  reactor.core.publisher.Mono.subscribe(Lorg/reactivestreams/Subscriber;)V+61
j  reactor.core.publisher.MonoSubscribeOn$SubscribeOnSubscriber.run()V+15
j  reactor.core.scheduler.WorkerTask.call()Ljava/lang/Void;+14
j  reactor.core.scheduler.WorkerTask.call()Ljava/lang/Object;+1
j  java.util.concurrent.FutureTask.run()V+39 java.base@22
j  java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run()V+28 java.base@22
j  java.util.concurrent.ThreadPoolExecutor.runWorker(Ljava/util/concurrent/ThreadPoolExecutor$Worker;)V+92 java.base@22
j  java.util.concurrent.ThreadPoolExecutor$Worker.run()V+5 java.base@22
j  java.lang.Thread.runWith(Ljava/lang/Object;Ljava/lang/Runnable;)V+5 java.base@22
j  java.lang.Thread.run()V+19 java.base@22
v  ~StubRoutines::call_stub 0x00000001125cc154

siginfo: si_signo: 10 (SIGBUS), si_code: 1 (BUS_ADRALN), si_addr: 0x00000001125d4d94

Registers:
x0=0x0000000000000000  x1=0x0000000000014037  x2=0x0000000000014037  x3=0x0000000000000000
x4=0x0000600003bd3130  x5=0x0000000175638b40  x6=0x0000000100000001  x7=0x000000012ea97e00
x8=0x0000000000000001  x9=0x0000000000000001 x10=0x0000000000000002 x11=0x0000000198273628
x12=0x0000000000000000 x13=0x00006000022e1200 x14=0x01000001fe426641 x15=0x00000001fe426640
x16=0x00000001965fc564 x17=0x00000002001d2d80 x18=0x0000000000000000 x19=0x00000001125d2bb0
x20=0x0000000175639250 x21=0x00000001027112a0 x22=0x0000000000000000 x23=0x0000000000000003
x24=0x00000001756392f8 x25=0x0000000605235368 x26=0x000000012b201a48 x27=0x000000060041bca0
x28=0x0000000138253400  fp=0x00000001756392c0  lr=0x9e038001125d4d94  sp=0x0000000175639250
pc=0x00000001125d4d94 cpsr=0x0000000060001000

Register to memory mapping:

x0 =0x0 is null
x1 =0x0000000000014037 is an unknown value
x2 =0x0000000000014037 is an unknown value
x3 =0x0 is null
x4 =0x0000600003bd3130 points into unknown readable memory: 0x0000000197792490 | 90 24 79 97 01 00 00 00
x5 =0x0000000175638b40 is pointing into the stack for thread: 0x0000000138253400
x6 =0x0000000100000001 is an unknown value
x7 =0x000000012ea97e00 is a thread
x8 =0x0000000000000001 is an unknown value
x9 =0x0000000000000001 is an unknown value
x10=0x0000000000000002 is an unknown value
x11=0x0000000198273628: __CFBasicHashTableSizes+0 in /System/Library/Frameworks/Foundation.framework/Versions/C/Foundation at 0x0000000197785000
x12=0x0 is null
x13=0x00006000022e1200 points into unknown readable memory: 0x9a178001977b1b84 | 84 1b 7b 97 01 80 17 9a
x14=0x01000001fe426641: OBJC_CLASS_$_NSClassicMapTable+0x1 in /System/Library/Frameworks/Foundation.framework/Versions/C/Foundation at 0x0000000197785000
x15=0x00000001fe426640: OBJC_CLASS_$_NSClassicMapTable+0 in /System/Library/Frameworks/Foundation.framework/Versions/C/Foundation at 0x0000000197785000
x16=0x00000001965fc564: os_unfair_lock_unlock+0 in /usr/lib/system/libsystem_platform.dylib at 0x00000001965fb000
x17=0x00000002001d2d80: <offset 0x66300d80> in /System/Library/Frameworks/AppKit.framework/Versions/C/AppKit at 0x0000000199ed2000
x18=0x0 is null
x19=0x00000001125d2bb0 is at code_begin+48 in an Interpreter codelet
result handlers for native calls  [0x00000001125d2b80, 0x00000001125d2bc8]  72 bytes
x20=0x0000000175639250 is pointing into the stack for thread: 0x0000000138253400
x21=0x00000001027112a0: _ZN19TemplateInterpreter13_active_tableE+0 in /opt/homebrew/Cellar/openjdk/22/libexec/openjdk.jdk/Contents/Home/lib/server/libjvm.dylib at 0x0000000101c20000
x22=0x0 is null
x23=0x0000000000000003 is an unknown value
x24=0x00000001756392f8 is pointing into the stack for thread: 0x0000000138253400
x25=
[error occurred during error reporting (printing register info), id 0xb, SIGSEGV (0xb) at pc=0x0000000101fb0c78]
x26=0x000000012b201a48 is pointing into metadata
x27=0x000000060041bca0 is an oop: java.lang.Object
{0x000000060041bca0} - klass: 'java/lang/Object'
- ---- fields (total size 2 words):
  x28=0x0000000138253400 is a thread
  fp=0x00000001756392c0 is pointing into the stack for thread: 0x0000000138253400
  lr=0x9e038001125d4d94 is an unknown value
  sp=0x0000000175639250 is pointing into the stack for thread: 0x0000000138253400  

```

</details>
</summary>

However, the request to insert a new person on my private machine seemed to work fine (Intel x86_64, Archlinux).  
This issue hasn't been investigated.