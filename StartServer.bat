@echo off
echo ==== MindMatch Dedicated server version 0.1 =====
echo = Please beware that this is a pre-alpha build  =
echo = Authors:                                      =
echo = Jurriaan Den Toonder                          =
echo = Govert de Gans                                =
echo = Yassin Omara                                  =
echo = Zoe van Steijn                                =
echo = Emma Jimmink                                  =
echo = Sebastiaan Hester                             =
echo = Copyright 2015-2016                           =
echo =================================================
echo.
echo.
java -classpath ".\server\lib\*;.\out\production\server" Server
pause