import matplotlib.pyplot as plt
import math

denstiy_average_velocity_path = 'parameters 6/fundamentalData.csv'

denstiy_average_velocity_file = open(denstiy_average_velocity_path,'r')

denstiy_average_velocity_array = denstiy_average_velocity_file.read().split('\n')

denstiy_average_velocity_list = list(map(lambda x: x.split(","), denstiy_average_velocity_array))

denstiy_average_velocity_file.close()

denstiy_average_velocity = []
density_array = []

for i in range(0, len(denstiy_average_velocity_list)):
  denstiy_average_velocity.append(float(denstiy_average_velocity_list[i][1]))
  density_array.append(float(denstiy_average_velocity_list[i][0]))

prom = []
density = []
errors = []

for i in range(0, int(len(denstiy_average_velocity) / 3)):
  prom.append((denstiy_average_velocity[i*3] + denstiy_average_velocity[(i*3)+1] + denstiy_average_velocity[(i*3)+2]) / 3)
  density.append(density_array[i*3])
  errors.append(math.sqrt(((denstiy_average_velocity[i*3] - prom[i])**2 + (denstiy_average_velocity[i*3 + 1] - prom[i])**2 + (denstiy_average_velocity[i*3 + 2] - prom[i])**2 / 2)))

plt.plot(density, prom, label="")
plt.errorbar(density, prom, yerr=errors, fmt='.')

FONTSIZE = 20
# plt.legend(loc='top right', fontsize=FONTSIZE)
plt.ylabel('Average velocity [m/s]', fontsize=FONTSIZE)
plt.xlabel('Density [1/m²]', fontsize=FONTSIZE)
plt.tick_params(axis='both', which='major', labelsize=FONTSIZE)

plt.show()