import sys
import os
version = "1.0-SNAPSHOT"
os.environ["JAVA_HOME"] = "/usr/lib/jvm/temurin-21-jdk-arm64"
os.environ["CLASSPATH"] = f"{sys.argv[1]}/libs/framework-{version}-all.jar"

from jnius import autoclass
from direct.showbase.ShowBase import ShowBase
import threading

Nightfall = autoclass("org.nightfall.Nightfall")
nightfall = Nightfall()

nightfall.start()

def waitForStart():
    while not nightfall.started:
        pass
thread = threading.Thread(target = waitForStart)
thread.start()
thread.join()

nightfall.close()

class Graphics(ShowBase):
    def __init__(self):
        ShowBase.__init__(self)
        self.taskMgr.add(self.checkState, "CheckState")
    def end(self):
        self.userExit()
    def checkState(self, task):
        if nightfall.finished:
            self.end()
        return task.cont
Graphics().run()