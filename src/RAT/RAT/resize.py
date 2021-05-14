import os, sys
from PIL import Image

size = 16, 16
files = ["close2.png", "command icon2.png", "connect2.png", "disconnect2.png", "messagebox2.png", "mouse2.png", "port2.png", "restart icon2.png", "shutdown icon2.png"]
for filename in files:
    outfile = filename.replace("2", "")
    if filename != outfile:
        try:
            im = Image.open(filename)
            im.thumbnail(size, Image.ANTIALIAS)
            im.save(outfile, "PNG")
        except IOError:
            print "failed"
