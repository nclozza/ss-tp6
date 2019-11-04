import matplotlib.pyplot as plt
import math

denstiy_average_velocity_path = 'parameters 1/fundamentalData.csv'
moriand_tsukaguchi_denstiy_average_velocity_path = 'Moriand - Tsukaguchi/fundamentalData.csv'
helbing_denstiy_average_velocity_path = 'Helbing/fundamentalData.csv'
predtetschenski_milinski_denstiy_average_velocity_path = 'Predtetschenski - Milinski/fundamentalData.csv'
older_denstiy_average_velocity_path = 'Older/fundamentalData.csv'
weidman_denstiy_average_velocity_path = 'Weidman/fundamentalData.csv'

denstiy_average_velocity_file = open(denstiy_average_velocity_path,'r')
moriand_tsukaguchi_denstiy_average_velocity_file = open(moriand_tsukaguchi_denstiy_average_velocity_path,'r')
helbing_denstiy_average_velocity_file = open(helbing_denstiy_average_velocity_path,'r')
predtetschenski_milinski_denstiy_average_velocity_file = open(predtetschenski_milinski_denstiy_average_velocity_path,'r')
older_denstiy_average_velocity_file = open(older_denstiy_average_velocity_path,'r')
weidman_denstiy_average_velocity_file = open(weidman_denstiy_average_velocity_path,'r')

denstiy_average_velocity_array = denstiy_average_velocity_file.read().split('\n')
moriand_tsukaguchi_denstiy_average_velocity_array = moriand_tsukaguchi_denstiy_average_velocity_file.read().split('\n')
helbing_denstiy_average_velocity_array = helbing_denstiy_average_velocity_file.read().split('\n')
predtetschenski_milinski_denstiy_average_velocity_array = predtetschenski_milinski_denstiy_average_velocity_file.read().split('\n')
older_denstiy_average_velocity_array = older_denstiy_average_velocity_file.read().split('\n')
weidman_denstiy_average_velocity_array = weidman_denstiy_average_velocity_file.read().split('\n')

denstiy_average_velocity_list = list(map(lambda x: x.split(","), denstiy_average_velocity_array))
moriand_tsukaguchi_denstiy_average_velocity_list = list(map(lambda x: x.split(","), moriand_tsukaguchi_denstiy_average_velocity_array))
helbing_denstiy_average_velocity_list = list(map(lambda x: x.split(","), helbing_denstiy_average_velocity_array))
predtetschenski_milinski_denstiy_average_velocity_list = list(map(lambda x: x.split(","), predtetschenski_milinski_denstiy_average_velocity_array))
older_denstiy_average_velocity_list = list(map(lambda x: x.split(","), older_denstiy_average_velocity_array))
weidman_denstiy_average_velocity_list = list(map(lambda x: x.split(","), weidman_denstiy_average_velocity_array))

denstiy_average_velocity_file.close()
moriand_tsukaguchi_denstiy_average_velocity_file.close()
helbing_denstiy_average_velocity_file.close()
predtetschenski_milinski_denstiy_average_velocity_file.close()
older_denstiy_average_velocity_file.close()
weidman_denstiy_average_velocity_file.close()

denstiy_average_velocity = []
moriand_tsukaguchi_denstiy_average_velocity = []
helbing_denstiy_average_velocity = []
predtetschenski_milinski_denstiy_average_velocity = []
older_denstiy_average_velocity = []
weidman_denstiy_average_velocity = []

density_array = []
moriand_tsukaguchi_density_array = []
helbing_density_array = []
predtetschenski_milinski_density_array = []
older_density_array = []
weidman_density_array = []

for i in range(0, len(denstiy_average_velocity_list)):
  denstiy_average_velocity.append(float(denstiy_average_velocity_list[i][1]))
  density_array.append(float(denstiy_average_velocity_list[i][0]))

for i in range(0, len(moriand_tsukaguchi_denstiy_average_velocity_list)):
  moriand_tsukaguchi_denstiy_average_velocity.append(float(moriand_tsukaguchi_denstiy_average_velocity_list[i][1]))
  moriand_tsukaguchi_density_array.append(float(moriand_tsukaguchi_denstiy_average_velocity_list[i][0]))

for i in range(0, len(helbing_denstiy_average_velocity_list)):
  helbing_denstiy_average_velocity.append(float(helbing_denstiy_average_velocity_list[i][1]))
  helbing_density_array.append(float(helbing_denstiy_average_velocity_list[i][0]))

for i in range(0, len(predtetschenski_milinski_denstiy_average_velocity_list)):
  predtetschenski_milinski_denstiy_average_velocity.append(float(predtetschenski_milinski_denstiy_average_velocity_list[i][1]))
  predtetschenski_milinski_density_array.append(float(predtetschenski_milinski_denstiy_average_velocity_list[i][0]))

for i in range(0, len(older_denstiy_average_velocity_list)):
  older_denstiy_average_velocity.append(float(older_denstiy_average_velocity_list[i][1]))
  older_density_array.append(float(older_denstiy_average_velocity_list[i][0]))

for i in range(0, len(weidman_denstiy_average_velocity_list)):
  weidman_denstiy_average_velocity.append(float(weidman_denstiy_average_velocity_list[i][1]))
  weidman_density_array.append(float(weidman_denstiy_average_velocity_list[i][0]))

prom = []
density = []

for i in range(0, int(len(denstiy_average_velocity) / 3)):
  prom.append((denstiy_average_velocity[i*3] + denstiy_average_velocity[(i*3)+1] + denstiy_average_velocity[(i*3)+2]) / 3)
  density.append(density_array[i*3])

plt.scatter(helbing_density_array, helbing_denstiy_average_velocity, label="Helbing", marker="o", color="green",facecolors="none")
plt.scatter(moriand_tsukaguchi_density_array, moriand_tsukaguchi_denstiy_average_velocity, label="Mori & Tsukaguchi", marker="^", color="red", facecolors="none")
plt.scatter(predtetschenski_milinski_density_array, predtetschenski_milinski_denstiy_average_velocity, label="Predtetschenski & Milinski", marker="v", color="black",facecolors="none")
# plt.scatter(older_density_array, older_denstiy_average_velocity, label="Older", marker="x", color="orange")
plt.scatter(weidman_density_array, weidman_denstiy_average_velocity, label="Weidman", marker="x", color="orange")
plt.plot(density, prom, label="Group 3 (CPM)")
# plt.errorbar(density, prom, yerr=errors, fmt='.')

FONTSIZE = 20
plt.legend(loc='upper right', fontsize=FONTSIZE)
plt.ylabel('Average velocity [m/s]', fontsize=FONTSIZE)
plt.xlabel('Density [1/m²]', fontsize=FONTSIZE)
plt.tick_params(axis='both', which='major', labelsize=FONTSIZE)

plt.show()