import matplotlib.pyplot as plt
import math

average_velocity_path = 'n = 400/averageVelocity.csv'

average_velocity_file = open(average_velocity_path,'r')

average_velocity_array = average_velocity_file.read().split('\n')

average_velocity_list = list(map(lambda x: x.split(","), average_velocity_array))

average_velocity_file.close()

average_velocity = []
time_array = []

for i in range(0, len(average_velocity_list)):
  average_velocity.append(float(average_velocity_list[i][1]))
  time_array.append(float(average_velocity_list[i][0]))

plt.plot(time_array, average_velocity, label='Average Velocity')

FONTSIZE = 20
plt.legend(loc='top right', fontsize=FONTSIZE)
plt.ylabel('Energy [J]', fontsize=FONTSIZE)
plt.xlabel('Time [s]', fontsize=FONTSIZE)
plt.tick_params(axis='both', which='major', labelsize=FONTSIZE)

plt.show()