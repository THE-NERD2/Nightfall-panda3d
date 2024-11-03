import sys
import os
os.environ["JAVA_HOME"] = "/usr/lib/jvm/temurin-21-jdk-arm64"
os.environ["CLASSPATH"] = f"{sys.argv[1]}/libs/nightfall.jar"

from jnius import autoclass
from direct.showbase.ShowBase import ShowBase

Nightfall = autoclass("org.nightfall.Nightfall")
nightfall = Nightfall()
class Graphics(ShowBase):
    def __init__(self):
        ShowBase.__init__(self)
        self.taskMgr.add(self.checkFinishedTask, "CheckFinishedTask")
    def checkFinishedTask(self, task):
        if nightfall.isFinished():
            self.userExit()
        return task.cont
nightfall.start()
Graphics().run()